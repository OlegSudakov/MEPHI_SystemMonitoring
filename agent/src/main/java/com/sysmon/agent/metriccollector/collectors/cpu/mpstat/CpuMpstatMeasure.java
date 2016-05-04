package com.sysmon.agent.metriccollector.collectors.cpu.mpstat;

import com.sysmon.agent.measure.Measure;

public class CpuMpstatMeasure extends Measure<CpuMpstatMeasureValue>
{
    public CpuMpstatMeasure(
            long timestamp,
            CpuMpstatMeasureValue value
    )
    {
        super(timestamp, value);
    }
}
