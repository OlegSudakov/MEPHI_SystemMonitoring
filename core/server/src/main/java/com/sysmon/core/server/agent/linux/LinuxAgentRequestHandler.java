package com.sysmon.core.server.agent.linux;

import com.sysmon.common.communication.LinuxAgentProtocol.LinuxAgentRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.apache.log4j.Logger;

public class LinuxAgentRequestHandler extends ChannelOutboundHandlerAdapter
{
    private final static Logger log = Logger.getLogger(LinuxAgentRequestHandler.class);

    private final String host;
    private final int port;

    public LinuxAgentRequestHandler(
            String host,
            int port
    )
    {
        this.host = host;
        this.port = port;
    }

    @Override
    public void write(
            ChannelHandlerContext ctx,
            Object msg,
            ChannelPromise promise
    ) throws Exception
    {
        log.debug(String.format("write context: %s", ctx));
        if (msg instanceof LinuxAgentRequest) {
            LinuxAgentRequest linuxAgentRequest = (LinuxAgentRequest) msg;
            log.debug(String.format("Processing request to host %s port %d: %s", host, port, linuxAgentRequest));
            super.write(ctx, msg, promise);
        }
    }
}
