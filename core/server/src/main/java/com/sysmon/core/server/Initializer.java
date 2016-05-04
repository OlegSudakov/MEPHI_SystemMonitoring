package com.sysmon.core.server;

import com.sysmon.core.dal.dao.AgentDao;
import com.sysmon.core.dal.dao.MetricDao;
import com.sysmon.core.dal.dao.MetricTypeDao;
import com.sysmon.core.server.agent.CommonAgentConnectionManager;
import com.sysmon.core.server.metric.CommonMetricManager;
import com.sysmon.core.server.metric.MetricTypeManager;
import com.sysmon.core.server.utils.AgentEntityToAgentConnectionConverter;
import com.sysmon.core.server.utils.MetricEntityToMetricConverter;
import com.sysmon.core.server.utils.MetricTypeEntityToMetricTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer
{
    @Autowired
    public Initializer(
            MetricTypeDao metricTypeDao,
            MetricTypeManager metricTypeManager,
            MetricTypeEntityToMetricTypeConverter metricTypeEntityToMetricTypeConverter,

            AgentDao agentDao,
            CommonAgentConnectionManager commonAgentConnectionManager,
            AgentEntityToAgentConnectionConverter agentEntityToAgentConnectionConverter,

            MetricDao metricDao,
            CommonMetricManager commonMetricManager,
            MetricEntityToMetricConverter metricEntityToMetricConverter
    )
    {
        metricTypeDao.getAll().stream()
                .parallel()
                .map(metricTypeEntityToMetricTypeConverter)
                .sequential()
                .forEach(metricTypeManager::add);

        agentDao.getAll().stream()
                .parallel()
                .map(agentEntityToAgentConnectionConverter)
                .sequential()
                .forEach(commonAgentConnectionManager::add);

        metricDao.getAll().stream()
                .parallel()
                .map(metricEntityToMetricConverter)
                .sequential()
                .forEach(commonMetricManager::add);
    }
}
