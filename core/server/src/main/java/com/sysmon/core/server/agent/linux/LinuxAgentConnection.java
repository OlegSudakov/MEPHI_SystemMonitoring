package com.sysmon.core.server.agent.linux;

import com.sysmon.util.netty.Log4jToNettyLogLevelConverter;
import com.sysmon.common.communication.LinuxAgentProtocol.*;
import com.sysmon.core.server.agent.AgentConnectionImpl;
import com.sysmon.core.server.agent.AgentConnectionType;
import com.sysmon.core.server.agent.AgentRequest;
import com.sysmon.core.server.agent.AgentResponse;
import com.google.common.util.concurrent.SettableFuture;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.Logger;
import org.javatuples.Pair;

import java.net.InetSocketAddress;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

public class LinuxAgentConnection extends AgentConnectionImpl<LinuxAgentMetric>
{
    private static final Logger log = Logger.getLogger(LinuxAgentConnection.class);

    private static final long requestPerformTimeoutInSeconds = 10;

    private EventLoopGroup workerGroup;
    private Channel channel;

    private volatile boolean started;

    private final String host;
    private final Integer port;

    private final ConcurrentMap<Long, Pair<LinuxAgentRequest, SettableFuture<Optional<LinuxAgentResponse>>>> requestResponseMap = new ConcurrentHashMap<>();

    private final AgentRequestToProtoLinuxAgentRequest agentRequestToProtoLinuxAgentRequest = new AgentRequestToProtoLinuxAgentRequest();
    private final AtomicLong transactionId = new AtomicLong(0);

    public LinuxAgentConnection(
            Long id,
            String host,
            Integer port
    )
    {
        super(id, AgentConnectionType.LINUX);
        if (host == null) {
            throw new IllegalArgumentException("Jmx agent connection host can not be null");
        }
        if (port == null) {
            throw new IllegalArgumentException("Jmx agent connection port can not be null");
        }
        this.host = host;
        this.port = port;
    }

    @Override
    public String toString()
    {
        return "LinuxAgentConnection{" +
                "id=" + id +
                ", agentConnectionType=" + agentConnectionType +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", started=" + started +
                '}';
    }

    public void start()
    {
        started = true;
        log.info(String.format("Starting netty tcp connection to host: %s port: %d", host, port));
        new Thread(() -> {
            workerGroup = new NioEventLoopGroup(1);
            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 100)
                        .option(ChannelOption.SO_KEEPALIVE, true)
                        .remoteAddress(new InetSocketAddress(host, port))
                        .handler(
                                new ChannelInitializer<SocketChannel>()
                                {
                                    @Override
                                    public void initChannel(SocketChannel ch) throws Exception
                                    {
                                        ch.pipeline()
                                                .addLast(new ProtobufVarint32FrameDecoder())
                                                .addLast(new ProtobufDecoder(LinuxAgentResponse.getDefaultInstance()))
                                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                                .addLast(new ProtobufEncoder())

                                                .addLast(
                                                        new LinuxAgentHandler(
                                                                new LinuxAgentResponseHandler(host, port, requestResponseMap),
                                                                new LinuxAgentRequestHandler(host, port)
                                                        )
                                                );
                                    }
                                });

                LogLevel logLevel = new Log4jToNettyLogLevelConverter().apply(log.getLevel());
                if (logLevel != null) {
                    b.handler(new LoggingHandler(logLevel));
                }

                // Start the server.
                ChannelFuture f = b.connect();
                channel = f.channel();
                f.sync();

                // Wait until the server socket is closed.
                f.channel().closeFuture().sync();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                if (workerGroup != null) {
                    workerGroup.shutdownGracefully();
                } else {
                    log.error("Error during shutting down executor. Closed connection could be caused by error.");
                }
            }
        }).start();
    }

    public void stop()
    {
        try {
            if (channel != null) {
                ChannelFuture cf = channel.close();
                cf.awaitUninterruptibly();
                if (workerGroup != null) {
                    workerGroup.shutdownGracefully();
                }
            }
            started = false;
        } catch (Throwable t) {
            log.error(String.format("Error closing connection at host %s port %d", host, port), t);
        } finally {
            channel = null;
            workerGroup = null;
        }
    }

    @Override
    protected void finalize() throws Throwable
    {
        stop();
        super.finalize();
    }

    public final String getHost()
    {
        return host;
    }

    public final int getPort()
    {
        return port;
    }

    public final boolean isStarted()
    {
        return started;
    }

    @Override
    public CompletableFuture<Optional<AgentResponse>> handle(AgentRequest<LinuxAgentMetric> agentRequest)
    {
        LinuxAgentRequest linuxAgentRequest = agentRequestToProtoLinuxAgentRequest.apply(agentRequest, transactionId.getAndIncrement());
        SettableFuture<Optional<LinuxAgentResponse>> linuxAgentResponseSettableFuture = SettableFuture.create();
        return CompletableFuture
                .supplyAsync(() -> {
                    requestResponseMap.put(linuxAgentRequest.getId(), Pair.with(linuxAgentRequest, linuxAgentResponseSettableFuture));
                    if (channel.isActive()) {
                        channel.writeAndFlush(linuxAgentRequest);
                    } else {
                        log.error("Channel is not active, cannot proceed request");
                        return Optional.<AgentResponse>empty();
                    }
                    try {
                        Optional<LinuxAgentResponse> linuxAgentResponseOptional = linuxAgentResponseSettableFuture.get(requestPerformTimeoutInSeconds, TimeUnit.SECONDS);
                        if (linuxAgentResponseOptional.isPresent()) {
                            return new LinuxAgentResponseToAgentResponse().apply(linuxAgentResponseOptional.get(), agentRequest);
                        }
                    } catch (TimeoutException e) {
                        log.error(String.format("Cannot obtain agent response due to timeout: %d secoond(s) left", requestPerformTimeoutInSeconds));
                    } catch (Exception e) {
                        log.error("Cannot obtain agent response due to error", e);
                    } finally {
                        requestResponseMap.remove(linuxAgentRequest.getId());
                    }
                    return Optional.<AgentResponse>empty();
                })
                .exceptionally(t -> {
                    log.error("Cannot obtain agent response due to error", t);
                    return null;
                });
    }

}
