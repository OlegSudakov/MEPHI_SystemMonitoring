package com.sysmon.agent.metriccollector.collectors.creators;

import com.sysmon.agent.metriccollector.collectors.api.MetricCollector;
import com.sysmon.agent.metriccollector.collectors.api.MetricCollectorTask;
import com.sysmon.agent.metriccollector.collectors.command.CommandMetricCollector;
import com.sysmon.agent.metriccollector.collectors.command.CommandMetricCollectorTask;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMetricCollector;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMetricCollectorTask;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMetricCollector;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMetricCollectorTask;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MetricParserCreator implements Function<MetricCollectorTask, MetricCollector>
{
    @Override
    public MetricCollector apply(MetricCollectorTask metricCollectorTask)
    {
        if (metricCollectorTask instanceof CpuMpstatMetricCollectorTask) {
            return new CpuMpstatMetricCollector((CpuMpstatMetricCollectorTask) metricCollectorTask);
        } else if (metricCollectorTask instanceof MemoryFreeMetricCollectorTask) {
            return new MemoryFreeMetricCollector((MemoryFreeMetricCollectorTask) metricCollectorTask);
        } else if (metricCollectorTask instanceof CommandMetricCollectorTask) {
            return new CommandMetricCollector();
        }
        return null;
    }
}
