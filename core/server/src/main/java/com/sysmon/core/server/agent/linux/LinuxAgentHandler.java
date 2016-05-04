package com.sysmon.core.server.agent.linux;

import io.netty.channel.CombinedChannelDuplexHandler;
import org.apache.log4j.Logger;

public class LinuxAgentHandler extends CombinedChannelDuplexHandler<LinuxAgentResponseHandler, LinuxAgentRequestHandler>
{
    public LinuxAgentHandler(
            LinuxAgentResponseHandler inboundHandler,
            LinuxAgentRequestHandler outboundHandler
    )
    {
        super(inboundHandler, outboundHandler);
    }
}
