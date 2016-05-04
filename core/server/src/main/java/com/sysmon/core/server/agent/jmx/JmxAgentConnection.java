package com.sysmon.core.server.agent.jmx;

import com.sysmon.core.server.agent.AgentConnectionImpl;
import com.sysmon.core.server.agent.AgentConnectionType;
import com.sysmon.core.server.agent.AgentRequest;
import com.sysmon.core.server.agent.AgentResponse;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class JmxAgentConnection extends AgentConnectionImpl<JmxAgentMetric>
{
    protected final String host;
    protected final Integer port;

    public JmxAgentConnection(
            Long id,
            String host,
            Integer port
    )
    {
        super(id, AgentConnectionType.JMX);
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
                '}';
    }

    @Override
    public CompletableFuture<Optional<AgentResponse>> handle(AgentRequest<JmxAgentMetric> agentRequest)
    {
        return CompletableFuture.supplyAsync(Optional::empty);
    }
}
