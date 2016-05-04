package com.sysmon.core.server.agent;

import com.sysmon.core.server.metric.collectible.CollectibleMetric;

public interface AgentConnection<T extends CollectibleMetric> extends AgentRequestHandler<T>
{
    Long getId();

    AgentConnectionType getAgentConnectionType();
}
