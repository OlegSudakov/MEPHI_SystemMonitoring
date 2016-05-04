package com.sysmon.agent.commandexecutor.impl;

import com.sysmon.agent.commandexecutor.api.CommandExecutionResult;
import com.sysmon.agent.commandexecutor.api.CommandExecutor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

@Component
@Conditional(ProdCommandExecutorEnabled.class)
public class ProdCommandExecutor implements CommandExecutor
{
    private static final Logger log = Logger.getLogger(CommandExecutor.class);

    @Override
    public CommandExecutionResult apply(String command)
    {
        StringBuilder outputBuilder = new StringBuilder();
        String output;
        int exitValue = 0;
        Process pr;
        Long startExecutionMillis;
        Long endExecutionMillis;
        try {
            startExecutionMillis = System.currentTimeMillis();
            pr = Runtime.getRuntime().exec(command);
            endExecutionMillis = System.currentTimeMillis();
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                outputBuilder.append(line + "\n");
            }
            output = outputBuilder.toString();
            log.debug(String.format("Command %s was executed with getOutput:\n%s", command, output));
        } catch (IOException e) {
            log.error(String.format("I/O exception during execution command '%s'", command));
            return null;
        }

        long halfOfDiff = (endExecutionMillis - startExecutionMillis) / 2;
        Date executionDateTime = new Date(startExecutionMillis + halfOfDiff);

        return new CommandExecutionResultImpl(output, exitValue, executionDateTime);
    }
}
