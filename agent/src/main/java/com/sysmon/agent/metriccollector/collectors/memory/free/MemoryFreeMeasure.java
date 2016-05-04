package com.sysmon.agent.metriccollector.collectors.memory.free;

import com.sysmon.agent.measure.Measure;

public class MemoryFreeMeasure extends Measure<MemoryFreeMeasureValue>
{
    public MemoryFreeMeasure(
            long timestamp,
            MemoryFreeMeasureValue value
    )
    {
        super(timestamp, value);
    }
}
