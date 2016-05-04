package com.sysmon.core.server.agent;

import com.sysmon.core.server.metric.collectible.CollectibleMetric;

import java.util.Collection;
import java.util.Optional;

public interface AgentConnectionManager<T extends AgentConnection, V extends CollectibleMetric> extends AgentRequestHandler<V>
{
    void add(T agentConnection);

    void remove(T agentConnection);

    void remove(Long id);

    Collection<T> getAll();

    Optional<T> getById(Long id);
}
