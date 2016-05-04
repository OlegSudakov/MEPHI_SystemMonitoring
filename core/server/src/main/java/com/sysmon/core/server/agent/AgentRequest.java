package com.sysmon.core.server.agent;

import com.sysmon.core.server.metric.MetricRequest;
import com.sysmon.core.server.metric.collectible.CollectibleMetric;

import java.util.Collection;

public class AgentRequest<T extends CollectibleMetric> extends MetricRequest<T>
{
    public AgentRequest(Collection<T> metrics)
    {
        super(metrics);
    }

    @Override
    public String toString()
    {
        return "AgentRequest{" +
                "metrics=" + metrics +
                '}';
    }
}
