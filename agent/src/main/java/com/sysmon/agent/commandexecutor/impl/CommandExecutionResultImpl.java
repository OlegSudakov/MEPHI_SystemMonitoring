package com.sysmon.agent.commandexecutor.impl;

import com.sysmon.agent.commandexecutor.api.CommandExecutionResult;

import java.util.Date;

public class CommandExecutionResultImpl implements CommandExecutionResult
{
    private String output;
    private int exitValue;
    private Date executionDateTime;

    public CommandExecutionResultImpl(
            String output,
            int exitValue,
            Date executionDateTime
    )
    {
        this.output = output;
        this.exitValue = exitValue;
        this.executionDateTime = executionDateTime;
    }

    @Override
    public String getOutput()
    {
        return output;
    }

    @Override
    public int getExitValue()
    {
        return exitValue;
    }

    @Override
    public Date executionDateTime()
    {
        return executionDateTime;
    }
}
