package com.sysmon.core.server.agent.jmx;

import com.sysmon.core.server.agent.AgentRequest;
import com.sysmon.core.server.agent.AgentResponse;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
public class JmxAgentConnectionManagerImpl implements JmxAgentConnectionManager
{
    @Override
    public void add(JmxAgentConnection agentConnection)
    {

    }

    @Override
    public void remove(JmxAgentConnection agentConnection)
    {
        remove(agentConnection.getId());
    }

    @Override
    public void remove(Long id)
    {

    }

    @Override
    public Collection<JmxAgentConnection> getAll()
    {
        return Collections.emptyList();
    }

    @Override
    public Optional<JmxAgentConnection> getById(Long id)
    {
        return Optional.empty();
    }

    @Override
    public CompletableFuture<Optional<AgentResponse>> handle(AgentRequest<JmxAgentMetric> agentRequest)
    {
        return CompletableFuture.supplyAsync(Optional::empty);
    }
}
