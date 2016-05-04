package com.sysmon.agent.metriccollector.collectors.command;

public class CommandMeasureValue
{
    private int exitValue;
    private String output;

    public CommandMeasureValue(
            int exitValue,
            String output
    )
    {
        this.exitValue = exitValue;
        this.output = output;
    }

    @Override
    public String toString()
    {
        return "CommandMeasureValue{" +
                "exitValue=" + exitValue +
                ", output='" + output + '\'' +
                '}';
    }

    public int getExitValue()
    {
        return exitValue;
    }

    public String getOutput()
    {
        return output;
    }
}
