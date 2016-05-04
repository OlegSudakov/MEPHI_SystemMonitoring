package com.sysmon.core.server.metric;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface MetricRequestHandler<T extends Metric>
{
    CompletableFuture<Optional<MeasureResponse>> handle(MetricRequest<T> agentRequest);
}
