package com.sysmon.agent.metriccollector.collectors.command;

import com.sysmon.agent.metriccollector.collectors.api.MetricCollectorTask;
import org.apache.commons.configuration.Configuration;

public class CommandMetricCollectorTask extends MetricCollectorTask
{
    private String command;

    public CommandMetricCollectorTask(String command)
    {
        this.command = command;
    }

    public CommandMetricCollectorTask(Configuration config)
    {
        this(config.getString("command"));
    }

    @Override
    public String toString()
    {
        return "CommandMetricCollectorTask{" +
                "command='" + command + '\'' +
                '}';
    }

    public String getCommand()
    {
        return command;
    }
}
