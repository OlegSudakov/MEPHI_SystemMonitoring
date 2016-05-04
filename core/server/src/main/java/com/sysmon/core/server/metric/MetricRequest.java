package com.sysmon.core.server.metric;

import com.google.common.collect.ImmutableSet;

import java.util.Collection;

public class MetricRequest<T extends Metric>
{
    protected final ImmutableSet<T> metrics;

    public MetricRequest(Collection<T> metrics)
    {
        this.metrics = ImmutableSet.copyOf(metrics);
    }

    @Override
    public String toString()
    {
        return "MetricRequest{" +
                "metrics=" + metrics +
                '}';
    }

    public Collection<T> getMetrics()
    {
        return metrics;
    }
}
