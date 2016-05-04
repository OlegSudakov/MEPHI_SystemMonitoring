package com.sysmon.agent.metriccollector.collectors.memory.free;

import com.sysmon.agent.metriccollector.collectors.api.MetricCollectorTask;
import org.apache.commons.configuration.Configuration;

public class MemoryFreeMetricCollectorTask extends MetricCollectorTask
{
    private final boolean totalMemoryEnabled;
    private final boolean freeMemoryEnabled;
    private final boolean totalSwapEnabled;
    private final boolean freeSwapEnabled;

    public MemoryFreeMetricCollectorTask(
            boolean totalMemoryEnabled,
            boolean freeMemoryEnabled,
            boolean totalSwapEnabled,
            boolean freeSwapEnabled
    )
    {
        this.totalMemoryEnabled = totalMemoryEnabled;
        this.freeMemoryEnabled = freeMemoryEnabled;
        this.totalSwapEnabled = totalSwapEnabled;
        this.freeSwapEnabled = freeSwapEnabled;
    }

    public MemoryFreeMetricCollectorTask(Configuration config)
    {
        this(
                config.containsKey("totalMemoryEnabled") && config.getBoolean("totalMemoryEnabled"),
                config.containsKey("freeMemoryEnabled") && config.getBoolean("freeMemoryEnabled"),
                config.containsKey("totalSwapEnabled") && config.getBoolean("totalSwapEnabled"),
                config.containsKey("freeSwapEnabled") && config.getBoolean("freeSwapEnabled")
        );
    }

    @Override
    public String toString()
    {
        return "MemoryFreeMetricCollectorTask{" +
                "totalMemoryEnabled=" + totalMemoryEnabled +
                ", freeMemoryEnabled=" + freeMemoryEnabled +
                ", totalSwapEnabled=" + totalSwapEnabled +
                ", freeSwapEnabled=" + freeSwapEnabled +
                '}';
    }

    public boolean isTotalMemoryEnabled()
    {
        return totalMemoryEnabled;
    }

    public boolean isFreeMemoryEnabled()
    {
        return freeMemoryEnabled;
    }

    public boolean isTotalSwapEnabled()
    {
        return totalSwapEnabled;
    }

    public boolean isFreeSwapEnabled()
    {
        return freeSwapEnabled;
    }
}
