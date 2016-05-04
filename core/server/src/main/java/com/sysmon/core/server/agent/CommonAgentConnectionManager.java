package com.sysmon.core.server.agent;

import com.sysmon.core.server.metric.collectible.CollectibleMetric;

import java.util.Collection;
import java.util.Optional;

public interface CommonAgentConnectionManager extends AgentConnectionManager<AgentConnection, CollectibleMetric>
{
    Collection<AgentConnection> getAgentConnections(AgentConnectionType agentConnectionType);

    Optional<? extends AgentConnection> getAgentConnection(
            AgentConnectionType agentConnectionType,
            Long id
    );
}
