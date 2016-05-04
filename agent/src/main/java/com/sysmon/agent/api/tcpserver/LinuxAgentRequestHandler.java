package com.sysmon.agent.api.tcpserver;

import com.sysmon.agent.api.util.protobuf.MeasureToResponse;
import com.sysmon.agent.metriccollector.MetricCollectorService;
import com.sysmon.agent.api.util.protobuf.RequestToMetricCollectorTaskConverter;
import com.sysmon.common.communication.LinuxAgentProtocol.*;
import com.sysmon.common.communication.LinuxAgentProtocol.LinuxAgentResponse.Error;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class LinuxAgentRequestHandler extends SimpleChannelInboundHandler<LinuxAgentRequest>
{
    private static final Logger log = Logger.getLogger(LinuxAgentRequestHandler.class);

    private TcpServerInterfaceConfig config;

    private MetricCollectorService metricCollectorService;

    public LinuxAgentRequestHandler(
            TcpServerInterfaceConfig config,
            MetricCollectorService metricCollectorService
    )
    {
        this.config = config;
        this.metricCollectorService = metricCollectorService;
    }

    @Override
    protected void channelRead0(
            ChannelHandlerContext context,
            LinuxAgentRequest request
    ) throws Exception
    {
        CompletableFuture
                .runAsync(() ->
                        context.writeAndFlush(
                                LinuxAgentResponse.newBuilder()
                                        .addAllResponses(
                                                request.getRequestsList().stream()
                                                        .parallel()
                                                        .map(new RequestToMetricCollectorTaskConverter())
                                                        .map(metricCollectorService)
                                                        .map(CompletableFuture::join)
                                                        .map(new MeasureToResponse())
                                                        .collect(Collectors.toList())
                                        )
                                        .setId(request.getId())
                                        .build()
                        )
                )
                .exceptionally(t -> {
                    log.error("Error processing response", t);
                    context.writeAndFlush(
                            LinuxAgentResponse.newBuilder().setError(
                                    Error.newBuilder().setMessage(
                                            String.format("Error processing request with id %d: %s", request.getId(), t)
                                    )
                            )
                    );
                    return null;
                });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        log.debug(String.format("Client from %s became active in channel at host %s port %d", ctx.channel().remoteAddress(), config.getHost(), config.getPort()));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        log.debug(String.format("Client from %s became inactive in channel at host %s port %d", ctx.channel().remoteAddress(), config.getHost(), config.getPort()));
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx,
            Throwable cause
    ) throws Exception
    {
        log.error(String.format("Exception in channel at host %s port %d", config.getHost(), config.getPort()), cause);
    }
}
