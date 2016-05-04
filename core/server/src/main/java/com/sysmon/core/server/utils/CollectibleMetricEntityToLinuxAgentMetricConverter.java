package com.sysmon.core.server.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmon.core.dal.entity.metric.CollectibleMetricEntity;
import com.sysmon.core.server.agent.AgentConnectionType;
import com.sysmon.core.server.agent.CommonAgentConnectionManager;
import com.sysmon.core.server.agent.linux.LinuxAgentConnection;
import com.sysmon.core.server.agent.linux.LinuxAgentMetric;
import com.sysmon.core.server.agent.linux.LinuxAgentMetricParams;
import com.sysmon.core.server.metric.MetricTypeManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Function;

@Component
public class CollectibleMetricEntityToLinuxAgentMetricConverter implements Function<CollectibleMetricEntity, LinuxAgentMetric>
{
    private static final Logger log = Logger.getLogger(CollectibleMetricEntityToLinuxAgentMetricConverter.class);

    private final CommonAgentConnectionManager commonAgentConnectionManager;
    private final MetricTypeManager metricTypeManager;

    @Autowired
    public CollectibleMetricEntityToLinuxAgentMetricConverter(
            CommonAgentConnectionManager commonAgentConnectionManager,
            MetricTypeManager metricTypeManager
    )
    {
        this.commonAgentConnectionManager = commonAgentConnectionManager;
        this.metricTypeManager = metricTypeManager;
    }

    @Override
    public LinuxAgentMetric apply(CollectibleMetricEntity collectibleMetricEntity)
    {
        ObjectMapper mapper = new ObjectMapper();
        LinuxAgentMetricParams linuxAgentMetricParams = null;
        try {
            linuxAgentMetricParams = mapper.readValue(collectibleMetricEntity.getParameters(), LinuxAgentMetricParams.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can not convert parameters of linux agent metric");
        }
        return new LinuxAgentMetric(
                collectibleMetricEntity.getId(),
                metricTypeManager.getById(collectibleMetricEntity.getMetricTypeEntity().getId())
                        .orElseThrow(() -> new IllegalArgumentException(String.format("Unknown metric type of linux agent metric with id %d", collectibleMetricEntity.getId()))),
                collectibleMetricEntity.getStoringPeriod(),
                collectibleMetricEntity.getCron(),
                (LinuxAgentConnection) commonAgentConnectionManager.getAgentConnection(AgentConnectionType.LINUX, collectibleMetricEntity.getAgentEntity().getId())
                        .orElseThrow(() -> new IllegalArgumentException(String.format("Unknown agent of linux agent metric with id %d", collectibleMetricEntity.getId()))),
                linuxAgentMetricParams
        );
    }
}
