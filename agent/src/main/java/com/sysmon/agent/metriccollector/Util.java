package com.sysmon.agent.metriccollector;

import com.sysmon.agent.commandexecutor.api.CommandExecutionResult;
import com.sysmon.agent.commandexecutor.impl.CommandExecutionResultImpl;

import java.util.Date;

public class Util
{
    public CommandExecutionResult constructCommandExecutionResult(String command)
    {
        return new CommandExecutionResultImpl(command, 0, new Date());
    }
}
