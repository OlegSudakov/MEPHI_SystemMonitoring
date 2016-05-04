package com.sysmon.core.server.agent.linux;

import java.util.Map;

public class LinuxAgentMetricParams
{
    protected LinuxAgentMetricType linuxAgentMetricType;
    protected Map<LinuxAgentMetricParam, Object> params;

    public LinuxAgentMetricParams(){}

    public LinuxAgentMetricParams(
            LinuxAgentMetricType linuxAgentMetricType,
            Map<LinuxAgentMetricParam, Object> params
    )
    {
        if (linuxAgentMetricType == null) {
            throw new IllegalArgumentException("Linux agent metric type can not be null");
        }
        if (params == null) {
            LinuxAgentMetricParamsValidator.validate(linuxAgentMetricType, params);
        }
        this.linuxAgentMetricType = linuxAgentMetricType;
        this.params = params;
    }

    @Override
    public String toString()
    {
        return "LinuxAgentMetricParams{" +
                "linuxAgentMetricType=" + linuxAgentMetricType +
                ", params=" + params +
                '}';
    }

    public final LinuxAgentMetricType getLinuxAgentMetricType()
    {
        return linuxAgentMetricType;
    }

    public final Map<LinuxAgentMetricParam, Object> getParams()
    {
        return params;
    }
}
