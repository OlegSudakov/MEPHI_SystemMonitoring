package com.sysmon.core.server.agent.linux;

import com.sysmon.core.server.metric.collectible.CollectibleMetric;
import com.sysmon.core.server.metric.MetricType;

import java.util.Map;

public class LinuxAgentMetric extends CollectibleMetric
{
    protected final LinuxAgentMetricParams linuxAgentMetricParams;

    public LinuxAgentMetric(
            Long id,
            final MetricType metricType,
            Long storingPeriod,
            String cronString,
            LinuxAgentConnection agentConnection,
            final LinuxAgentMetricParams linuxAgentMetricParams
    )
    {
        super(id, metricType, storingPeriod, cronString, agentConnection);
        if (linuxAgentMetricParams == null) {
            throw new IllegalArgumentException("Linux agent metric params can not be null");
        }
        this.linuxAgentMetricParams = linuxAgentMetricParams;
    }

    public LinuxAgentMetric(
            Long id,
            final MetricType metricType,
            Long storingPeriod,
            String cron,
            LinuxAgentConnection agentConnection,
            final LinuxAgentMetricType linuxAgentMetricType,
            final Map<LinuxAgentMetricParam, Object> params
    )
    {
        super(id, metricType, storingPeriod, cron, agentConnection);
        linuxAgentMetricParams = new LinuxAgentMetricParams(linuxAgentMetricType, params);
    }

    @Override
    public String toString()
    {
        return "LinuxAgentMetric{" +
                "id=" + id +
                ", metricType=" + metricType +
                ", storingPeriod=" + storingPeriod +
                ", cronString='" + cronString + '\'' +
                ", agent=" + agent +
                ", linuxAgentMetricParams=" + linuxAgentMetricParams +
                '}';
    }

    public final LinuxAgentMetricParams getLinuxAgentMetricParams()
    {
        return linuxAgentMetricParams;
    }
}
