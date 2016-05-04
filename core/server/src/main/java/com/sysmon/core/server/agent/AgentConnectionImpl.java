package com.sysmon.core.server.agent;

import com.sysmon.core.server.metric.collectible.CollectibleMetric;

public abstract class AgentConnectionImpl<T extends CollectibleMetric> implements AgentConnection<T>
{
    protected final Long id;
    protected final AgentConnectionType agentConnectionType;

    public AgentConnectionImpl(
            Long id,
            AgentConnectionType agentConnectionType
    )
    {
        if (id == null) {
            throw new IllegalArgumentException("Agent connection id can not be null");
        }
        if (agentConnectionType == null) {
            throw new IllegalArgumentException("Agent connection type can not be null");
        }
        this.id = id;
        this.agentConnectionType = agentConnectionType;
    }

    @Override
    public String toString()
    {
        return "AgentConnectionImpl{" +
                "id=" + id +
                ", agentConnectionType=" + agentConnectionType +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof AgentConnectionImpl)) return false;

        AgentConnectionImpl<?> that = (AgentConnectionImpl<?>) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : (getAgentConnectionType() != null ? getAgentConnectionType().hashCode() : 0);
    }

    @Override
    public final Long getId()
    {
        return id;
    }

    @Override
    public final AgentConnectionType getAgentConnectionType()
    {
        return agentConnectionType;
    }
}
