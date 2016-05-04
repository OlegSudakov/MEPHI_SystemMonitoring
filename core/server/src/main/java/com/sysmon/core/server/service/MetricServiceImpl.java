package com.sysmon.core.server.service;

import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.validator.CronValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sysmon.core.dal.dao.AgentDao;
import com.sysmon.core.dal.dao.MetricDao;
import com.sysmon.core.dal.dao.MetricTypeDao;
import com.sysmon.core.dal.entity.agent.AgentEntity;
import com.sysmon.core.dal.entity.metric.CollectibleMetricEntity;
import com.sysmon.core.dal.entity.metric.MetricEntity;
import com.sysmon.core.dal.entity.metric.MetricTypeEntity;
import com.sysmon.core.server.agent.AgentConnection;
import com.sysmon.core.server.agent.AgentConnectionType;
import com.sysmon.core.server.agent.CommonAgentConnectionManager;
import com.sysmon.core.server.agent.jmx.JmxAgentConnection;
import com.sysmon.core.server.agent.jmx.JmxAgentMetric;
import com.sysmon.core.server.agent.jmx.JmxAgentMetricParams;
import com.sysmon.core.server.agent.linux.LinuxAgentConnection;
import com.sysmon.core.server.agent.linux.LinuxAgentMetric;
import com.sysmon.core.server.agent.linux.LinuxAgentMetricParam;
import com.sysmon.core.server.agent.linux.LinuxAgentMetricParams;
import com.sysmon.core.server.agent.linux.LinuxAgentMetricType;
import com.sysmon.core.server.metric.CommonMetricManager;
import com.sysmon.core.server.metric.MetricType;
import com.sysmon.core.server.metric.MetricTypeManager;
import com.sysmon.core.server.utils.MetricEntityToMetricConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class MetricServiceImpl implements MetricService
{
    private static final Logger log = Logger.getLogger(MetricServiceImpl.class);

    private final CommonAgentConnectionManager commonAgentConnectionManager;
    private final AgentDao agentDao;
    private final MetricTypeManager metricTypeManager;
    private final MetricTypeDao metricTypeDao;
    private final CommonMetricManager commonMetricManager;
    private final MetricDao metricDao;
    private final MetricEntityToMetricConverter metricEntityToMetricConverter;

    @Autowired
    public MetricServiceImpl(
            CommonAgentConnectionManager commonAgentConnectionManager,
            AgentDao agentDao,
            MetricTypeManager metricTypeManager,
            MetricTypeDao metricTypeDao,
            CommonMetricManager commonMetricManager,
            MetricDao metricDao,
            MetricEntityToMetricConverter metricEntityToMetricConverter
    )
    {
        this.commonAgentConnectionManager = commonAgentConnectionManager;
        this.agentDao = agentDao;
        this.metricTypeManager = metricTypeManager;
        this.metricTypeDao = metricTypeDao;
        this.commonMetricManager = commonMetricManager;
        this.metricDao = metricDao;
        this.metricEntityToMetricConverter = metricEntityToMetricConverter;
    }

    private void fillMetricEntity(
            MetricEntity metric,
            Long metricTypeId,
            Long storingPeriod,
            String cron
    )
    {
        Optional<MetricTypeEntity> metricTypeEntityOptional = metricTypeDao.getById(metricTypeId);
        if (!metricTypeEntityOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("No metric type with id %d found", metricTypeId));
        }
        metric.setMetricTypeEntity(metricTypeEntityOptional.get());
        metric.setStoringPeriod(storingPeriod);
        metric.setCron(cron);
    }

    private void fillCollectibleMetricEntity(
            CollectibleMetricEntity metric,
            Long metricTypeId,
            Long storingPeriod,
            String cron,
            Long agentId,
            byte[] parameters
    )
    {
        Optional<AgentEntity> agentEntityOptional = agentDao.getById(agentId);
        if (!agentEntityOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("No agent with id %d found", agentId));
        }
        fillMetricEntity(metric, metricTypeId, storingPeriod, cron);
        metric.setAgentEntity(agentEntityOptional.get());
        metric.setParameters(parameters);
    }

    private void validateMetricTypeId(Long metricTypeId)
    {
        Optional<MetricType> metricTypeEntityOptional = metricTypeManager.getById(metricTypeId);
        if (!metricTypeEntityOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("No metric type with id %d parameters found", metricTypeId));
        }
    }

    private void validateStoringPeriod(Long storingPeriod)
    {
        if (storingPeriod != null && storingPeriod < 0) {
            throw new IllegalArgumentException("Storing period can not be less than 0");
        }
    }

    private void validateCronString(String cronString)
    {
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
        CronValidator cronValidator = new CronValidator(cronDefinition);
        if (!cronValidator.isValid(cronString)) {
            throw new IllegalArgumentException(String.format("Cron expression is invalid %s", cronString));
        }
    }

    private void validateAgentId(
            Long agentId,
            AgentConnectionType agentConnectionType
    )
    {
        Optional<? extends AgentConnection> agentConnectionOptional = commonAgentConnectionManager.getAgentConnection(agentConnectionType, agentId);
        if (!agentConnectionOptional.isPresent() || !(agentConnectionType.equals(agentConnectionOptional.get().getAgentConnectionType()))) {
            throw new IllegalArgumentException(String.format("No linux agent with id %d found", agentId));
        }
    }

    @Override
//    @Transactional
    public void addLinuxAgentMetric(
            Long metricTypeId,
            Long storingPeriod,
            String cronString,
            Long agentId,
            LinuxAgentMetricType linuxAgentMetricType,
            Map<LinuxAgentMetricParam, Object> params
    )
    {
        validateMetricTypeId(metricTypeId);
        validateStoringPeriod(storingPeriod);
        validateCronString(cronString);
        validateAgentId(agentId, AgentConnectionType.LINUX);

        LinuxAgentMetricParams linuxAgentMetricParams = new LinuxAgentMetricParams(linuxAgentMetricType, params);

        CollectibleMetricEntity metricEntity = new CollectibleMetricEntity();
        byte[] parameters = constructLinuxAgentMetricParameters(linuxAgentMetricParams);
        if (parameters == null) {
            throw new IllegalArgumentException("Can not create linux agent metric with given parameters");
        }

        fillCollectibleMetricEntity(metricEntity, metricTypeId, storingPeriod, cronString, agentId, parameters);
        MetricEntity savedMetric = metricDao.save(metricEntity);

        Optional<MetricType> metricTypeOptional = metricTypeManager.getById(metricTypeId);
        Optional<? extends AgentConnection> linuxAgentConnectionOptional = commonAgentConnectionManager.getAgentConnection(AgentConnectionType.LINUX, agentId);

        LinuxAgentMetric metric = new LinuxAgentMetric(
                savedMetric.getId(),
                metricTypeOptional.get(),
                storingPeriod,
                cronString,
                (LinuxAgentConnection) linuxAgentConnectionOptional.get(),
                linuxAgentMetricParams
        );
        commonMetricManager.add(metric);
    }

    private byte[] constructLinuxAgentMetricParameters(LinuxAgentMetricParams linuxAgentMetricParams)
    {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return objectWriter.writeValueAsBytes(linuxAgentMetricParams);
        } catch (JsonProcessingException e) {
            log.error(String.format("Cannot convert linux agent metric params %s to byte array", linuxAgentMetricParams));
            return null;
        }
    }

    @Override
//    @Transactional
    public void addJmxAgentMetric(
            Long metricTypeId,
            Long storingPeriod,
            String cronString,
            Long agentId,
            String domainName,
            String objectName,
            String propertyName
    )
    {
        validateMetricTypeId(metricTypeId);
        validateStoringPeriod(storingPeriod);
        validateCronString(cronString);
        validateAgentId(agentId, AgentConnectionType.JMX);

        JmxAgentMetricParams jmxAgentMetricParams = new JmxAgentMetricParams(domainName, objectName, propertyName);

        CollectibleMetricEntity metricEntity = new CollectibleMetricEntity();
        byte[] parameters = constructJmxAgentMetricParameters(jmxAgentMetricParams);
        if (parameters == null) {
            throw new IllegalArgumentException("Can not create jmx agent metric with given parameters");
        }

        fillCollectibleMetricEntity(metricEntity, metricTypeId, storingPeriod, cronString, agentId, parameters);
        MetricEntity savedMetric = metricDao.save(metricEntity);

        Optional<MetricType> metricTypeOptional = metricTypeManager.getById(metricTypeId);
        Optional<? extends AgentConnection> jmxAgentConnectionOptional = commonAgentConnectionManager.getAgentConnection(AgentConnectionType.JMX, agentId);

        JmxAgentMetric metric = new JmxAgentMetric(
                savedMetric.getId(),
                metricTypeOptional.get(),
                storingPeriod,
                cronString,
                (JmxAgentConnection) jmxAgentConnectionOptional.get(),
                jmxAgentMetricParams
        );
        commonMetricManager.add(metric);
    }

    private byte[] constructJmxAgentMetricParameters(JmxAgentMetricParams jmxAgentMetricParams)
    {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return objectWriter.writeValueAsBytes(jmxAgentMetricParams);
        } catch (JsonProcessingException e) {
            log.error(String.format("Cannot convert jmx agent metric params %s", jmxAgentMetricParams));
            return null;
        }
    }
}
