package com.sysmon.agent.metriccollector.collectors.creators;

import com.sysmon.agent.measure.Measure;
import com.sysmon.agent.metriccollector.collectors.command.CommandMeasure;
import com.sysmon.agent.metriccollector.collectors.command.CommandMeasureValue;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMeasure;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMeasureValue;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMeasure;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMeasureValue;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MeasureCreator implements Function<Pair<Long, Object>, Measure>
{
    @Override
    public Measure apply(Pair<Long, Object> data)
    {
        Long timestamp = data.getValue0();
        Object measureValue = data.getValue1();
        if (measureValue instanceof CpuMpstatMeasureValue) {
            return new CpuMpstatMeasure(timestamp, (CpuMpstatMeasureValue) measureValue);
        } else if (measureValue instanceof MemoryFreeMeasureValue) {
            return new MemoryFreeMeasure(timestamp, (MemoryFreeMeasureValue) measureValue);
        } else if (measureValue instanceof CommandMeasureValue) {
            return new CommandMeasure(timestamp, (CommandMeasureValue) measureValue);
        }
        return null;
    }
}
