package com.sysmon.agent.metriccollector.collectors.api;

import com.sysmon.agent.commandexecutor.api.CommandExecutionResult;

import java.util.function.Function;

public interface MetricCollector<T> extends Function<CommandExecutionResult, T>
{
}
