package com.sysmon.agent.metriccollector.collectors.creators;

import com.sysmon.agent.metriccollector.collectors.api.MetricCollectorTask;
import com.sysmon.agent.metriccollector.collectors.command.CommandMetricCollectorTask;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMetricCollectorTask;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMetricCollectorTask;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MetricCollectorCommandCreator implements Function<MetricCollectorTask, String>
{
    @Override
    public String apply(MetricCollectorTask metricCollectorTask)
    {
        if (metricCollectorTask instanceof CpuMpstatMetricCollectorTask) {
            return "mpstat";
        } else if (metricCollectorTask instanceof MemoryFreeMetricCollectorTask) {
            return "free -m";
        } else if (metricCollectorTask instanceof CommandMetricCollectorTask) {
            return ((CommandMetricCollectorTask) metricCollectorTask).getCommand();
        }
        return null;
    }
}
