package com.sysmon.agent.commandexecutor.api;

import java.util.function.Function;

public interface CommandExecutor extends Function<String, CommandExecutionResult>
{
}
