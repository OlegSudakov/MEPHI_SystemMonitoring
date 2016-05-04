package com.sysmon.agent.api.tcpserver;

import com.sysmon.util.netty.Log4jToNettyLogLevelConverter;
import com.sysmon.agent.metriccollector.MetricCollectorService;
import com.sysmon.common.communication.LinuxAgentProtocol.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.Logger;

public class TcpServerInterface
{
    private static final Logger log = Logger.getLogger(TcpServerInterface.class);

    private static final int BOSS_THREADS_COUNT = 1;

    private final TcpServerInterfaceConfig interfaceConfig;
    private MetricCollectorService metricCollectorService;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel channel;

    public TcpServerInterface(
            TcpServerInterfaceConfig interfaceConfig,
            MetricCollectorService metricCollectorService
    )
    {
        this.interfaceConfig = interfaceConfig;
        this.metricCollectorService = metricCollectorService;
    }

    public void start()
    {
        log.info(String.format("Starting netty tcp interface at host: %s port: %d", interfaceConfig.getHost(), interfaceConfig.getPort()));
        new Thread(() -> {
            bossGroup = new NioEventLoopGroup(BOSS_THREADS_COUNT);
            workerGroup = new NioEventLoopGroup(interfaceConfig.getEventLoopThreadsCount());
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 100)
                        .option(ChannelOption.SO_KEEPALIVE, true)
                        .childHandler(
                                new ChannelInitializer<SocketChannel>()
                                {
                                    @Override
                                    public void initChannel(SocketChannel ch) throws Exception
                                    {
                                        ch.pipeline()
                                                .addLast(new ProtobufVarint32FrameDecoder())
                                                .addLast(new ProtobufDecoder(LinuxAgentRequest.getDefaultInstance()))
                                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                                .addLast(new ProtobufEncoder())

                                                .addLast(new LinuxAgentRequestHandler(interfaceConfig, metricCollectorService));
                                    }
                                });

                LogLevel logLevel = new Log4jToNettyLogLevelConverter().apply(log.getLevel());
                if (logLevel != null) {
                    b.handler(new LoggingHandler(logLevel));
                }

                // Start the server.
                ChannelFuture f = b.bind(interfaceConfig.getHost(), interfaceConfig.getPort());
                channel = f.channel();
                f.sync();

                // Wait until the server socket is closed.
                f.channel().closeFuture().sync();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                // Shut down all event loops to terminate all threads.
                if (bossGroup != null) {
                    bossGroup.shutdownGracefully();
                } else {
                    log.error("Error during shutting down executor. Closed connection could be cause of error.");
                }
                if (workerGroup != null) {
                    workerGroup.shutdownGracefully();
                } else {
                    log.error("Error during shutting down executor. Closed connection could be cause of error.");
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
                if (bossGroup != null) {
                    bossGroup.shutdownGracefully();
                }
            }
        } catch (Throwable t) {
            log.error(String.format("Error closing connection at host %s port %d", interfaceConfig.getHost(), interfaceConfig.getPort()), t);
        } finally {
            channel = null;
            bossGroup = null;
            workerGroup = null;
        }
    }

    @Override
    protected void finalize() throws Throwable
    {
        stop();
        super.finalize();
    }
}
