package com.sysmon.agent.metriccollector.collectors.command;

import com.sysmon.agent.commandexecutor.api.CommandExecutionResult;
import com.sysmon.agent.metriccollector.collectors.api.MetricCollector;

public class CommandMetricCollector implements MetricCollector<CommandMeasureValue>
{
    @Override
    public CommandMeasureValue apply(CommandExecutionResult commandExecutionResult)
    {
        return new CommandMeasureValue(commandExecutionResult.getExitValue(), commandExecutionResult.getOutput());
    }
}
