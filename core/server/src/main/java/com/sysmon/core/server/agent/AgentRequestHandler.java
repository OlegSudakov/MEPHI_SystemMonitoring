package com.sysmon.core.server.agent;

import com.sysmon.core.server.metric.collectible.CollectibleMetric;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface AgentRequestHandler<T extends CollectibleMetric>
{
    CompletableFuture<Optional<AgentResponse>> handle(AgentRequest<T> agentRequest);
}
