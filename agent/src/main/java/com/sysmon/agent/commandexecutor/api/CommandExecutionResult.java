package com.sysmon.agent.commandexecutor.api;

import java.util.Date;

public interface CommandExecutionResult
{
    String getOutput();
    int getExitValue();
    Date executionDateTime();
}
