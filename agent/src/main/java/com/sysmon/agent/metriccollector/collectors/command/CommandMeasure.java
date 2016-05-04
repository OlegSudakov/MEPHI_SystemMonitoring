package com.sysmon.agent.metriccollector.collectors.command;

import com.sysmon.agent.measure.Measure;

public class CommandMeasure extends Measure<CommandMeasureValue>
{
    public CommandMeasure(
            long timestamp,
            CommandMeasureValue value
    )
    {
        super(timestamp, value);
    }
}
