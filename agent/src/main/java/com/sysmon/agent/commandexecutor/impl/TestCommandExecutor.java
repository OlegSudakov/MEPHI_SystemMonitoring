package com.sysmon.agent.commandexecutor.impl;

import com.sysmon.agent.commandexecutor.api.CommandExecutionResult;
import com.sysmon.agent.commandexecutor.api.CommandExecutor;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Conditional(TestCommandExecutorEnabled.class)
public class TestCommandExecutor implements CommandExecutor
{
    @Override
    public CommandExecutionResult apply(String command)
    {
        switch (command) {
            case "free -m":
                return new CommandExecutionResultImpl(
                        "             total       used       free     shared    buffers     cached\n" +
                                "Mem:          3936       2100       1835         31        116        735\n" +
                                "-/+ buffers/cache:       1249       2686\n" +
                                "Swap:         4093        108       3985\n",
                        0,
                        new Date());
            case "mpstat":
                return new CommandExecutionResultImpl(
                        "Linux 3.19.0-42-generic (ubuntu) \t01/18/2016 \t_x86_64_\t(2 CPU)\n" +
                                "\n" +
                                "10:53:39 AM  CPU    %usr   %nice    %sys %iowait    %irq   %soft  %steal  %guest  %gnice   %idle\n" +
                                "10:53:39 AM  all    0.78    0.05    0.98    0.02    0.00    0.01    0.00    0.00    0.00   98.16\n",
                        0,
                        new Date());
            default:
                return new CommandExecutionResultImpl("", 0, new Date());
        }
    }
}
