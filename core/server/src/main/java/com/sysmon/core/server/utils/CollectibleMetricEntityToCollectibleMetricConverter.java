package com.sysmon.core.server.utils;

import com.sysmon.core.dal.entity.agent.AgentEntity;
import com.sysmon.core.dal.entity.agent.LinuxAgentEntity;
import com.sysmon.core.dal.entity.metric.CollectibleMetricEntity;
import com.sysmon.core.server.agent.CommonAgentConnectionManager;
import com.sysmon.core.server.metric.collectible.CollectibleMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CollectibleMetricEntityToCollectibleMetricConverter implements Function<CollectibleMetricEntity, CollectibleMetric>
{
    private final CollectibleMetricEntityToLinuxAgentMetricConverter collectibleMetricEntityToLinuxAgentMetricConverter;

    @Autowired
    public CollectibleMetricEntityToCollectibleMetricConverter(CollectibleMetricEntityToLinuxAgentMetricConverter collectibleMetricEntityToLinuxAgentMetricConverter)
    {
        this.collectibleMetricEntityToLinuxAgentMetricConverter = collectibleMetricEntityToLinuxAgentMetricConverter;
    }

    @Override
    public CollectibleMetric apply(CollectibleMetricEntity collectibleMetricEntity)
    {
        AgentEntity agentEntity = collectibleMetricEntity.getAgentEntity();
        if (agentEntity instanceof LinuxAgentEntity) {
            return collectibleMetricEntityToLinuxAgentMetricConverter.apply(collectibleMetricEntity);
        } else {
            throw new IllegalArgumentException(String.format("Unknown agent metric type for collectible metric with id %d", collectibleMetricEntity.getId()));
        }
    }
}
