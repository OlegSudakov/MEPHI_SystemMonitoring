package com.sysmon.core.server.agent.linux;

import com.sysmon.common.communication.LinuxAgentProtocol.*;
import com.google.common.util.concurrent.SettableFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;
import org.javatuples.Pair;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

public class LinuxAgentResponseHandler extends SimpleChannelInboundHandler<LinuxAgentResponse>
{
    private static final Logger log = Logger.getLogger(LinuxAgentResponseHandler.class);

    private final String host;
    private final int port;
    private final ConcurrentMap<Long, Pair<LinuxAgentRequest, SettableFuture<Optional<LinuxAgentResponse>>>> requestResponseMap;

    public LinuxAgentResponseHandler(
            String host,
            int port,
            ConcurrentMap<Long, Pair<LinuxAgentRequest, SettableFuture<Optional<LinuxAgentResponse>>>> requestResponseMap
    )
    {
        this.host = host;
        this.port = port;
        this.requestResponseMap = requestResponseMap;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        log.debug(String.format("Channel activated for host %s port %d", host, port));
        super.channelRegistered(ctx);
    }

    @Override
    protected void channelRead0(
            ChannelHandlerContext context,
            LinuxAgentResponse response
    ) throws Exception
    {
        log.debug(String.format("Received response: %s", response));
        Pair<LinuxAgentRequest, SettableFuture<Optional<LinuxAgentResponse>>> requestResponse = requestResponseMap.get(response.getId());
        if (requestResponse != null) {
            SettableFuture<Optional<LinuxAgentResponse>> future = requestResponse.getValue1();
            if (!response.hasError()) {
                future.set(Optional.of(response));
            } else {
                log.error(String.format("Response with id %d with error: %s", response.getId(), response.getError().getMessage()));
                future.set(Optional.empty());
            }
        } else {
            log.error(String.format("Got response for expired or failed request, %s", response));
        }
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx,
            Throwable cause
    ) throws Exception
    {
        log.error(String.format("Exception in channel at host %s port %d", host, port), cause);
    }
}