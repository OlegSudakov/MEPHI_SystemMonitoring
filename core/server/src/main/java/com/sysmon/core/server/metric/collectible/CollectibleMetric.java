package com.sysmon.core.server.metric.collectible;

import com.sysmon.core.server.agent.AgentConnection;
import com.sysmon.core.server.metric.Metric;
import com.sysmon.core.server.metric.MetricType;
import com.sysmon.core.server.metric.MetricTypeEnum;

public class CollectibleMetric extends Metric
{
    protected final AgentConnection agent;

    public CollectibleMetric(
            Long id,
            MetricType metricType,
            Long storingPeriod,
            String cronString,
            AgentConnection agent
    )
    {
        super(id, MetricTypeEnum.COLLECTIBLE, metricType, storingPeriod, cronString);
        if (agent == null) {
            throw new IllegalArgumentException("Metric agent can not be null");
        }
        this.agent = agent;
    }

    @Override
    public String toString()
    {
        return "CollectibleMetric{" +
                "id=" + id +
                ", metricTypeEnum=" + metricTypeEnum +
                ", metricType=" + metricType +
                ", storingPeriod=" + storingPeriod +
                ", cronString='" + cronString + '\'' +
                ", agent=" + agent +
                '}';
    }

    public final AgentConnection getAgentConnection()
    {
        return agent;
    }
}
