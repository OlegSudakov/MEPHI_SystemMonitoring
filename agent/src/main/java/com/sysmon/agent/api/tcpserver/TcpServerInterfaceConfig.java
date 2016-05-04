package com.sysmon.agent.api.tcpserver;

public class TcpServerInterfaceConfig
{
    private final String host;
    private final int port;
    private final int eventLoopThreadsCount;

    public TcpServerInterfaceConfig(
            String host,
            int port,
            int eventLoopThreadsCount
    )
    {
        this.host = host;
        this.port = port;
        this.eventLoopThreadsCount = eventLoopThreadsCount;
    }

    public String getHost()
    {
        return host;
    }

    public int getPort()
    {
        return port;
    }

    public int getEventLoopThreadsCount()
    {
        return eventLoopThreadsCount;
    }
}
