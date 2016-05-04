package com.sysmon.core.server.agent.jmx;

import com.sysmon.core.server.metric.collectible.CollectibleMetric;
import com.sysmon.core.server.metric.MetricType;

public class JmxAgentMetric extends CollectibleMetric
{
    protected final JmxAgentMetricParams jmxAgentMetricParams;

    public JmxAgentMetric(
            Long id,
            final MetricType metricType,
            Long storingPeriod,
            String cronString,
            JmxAgentConnection agent,
            final JmxAgentMetricParams jmxAgentMetricParams
    )
    {
        super(id, metricType, storingPeriod, cronString, agent);
        if (jmxAgentMetricParams == null) {
            throw new IllegalArgumentException("Jmx agent metric params can not be null");
        }
        this.jmxAgentMetricParams = jmxAgentMetricParams;
    }

    public JmxAgentMetric(
            Long id,
            final MetricType metricType,
            Long storingPeriod,
            String cron,
            final JmxAgentConnection agent,
            String domainName,
            String objectName,
            String propertyName
    )
    {
        super(id, metricType, storingPeriod, cron, agent);
        jmxAgentMetricParams = new JmxAgentMetricParams(domainName, objectName, propertyName);
    }

    @Override
    public String toString()
    {
        return "JmxAgentMetric{" +
                "id=" + id +
                ", metricType=" + metricType +
                ", storingPeriod=" + storingPeriod +
                ", cronString='" + cronString + '\'' +
                ", agent=" + agent +
                ", jmxaAentMetricParams='" + jmxAgentMetricParams + '\'' +
                '}';
    }

    public final JmxAgentMetricParams getJmxAgentMetricParams()
    {
        return jmxAgentMetricParams;
    }
}
