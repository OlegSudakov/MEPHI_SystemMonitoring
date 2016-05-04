package com.sysmon.core.server.service;

import com.sysmon.core.server.agent.linux.LinuxAgentMetricParam;
import com.sysmon.core.server.agent.linux.LinuxAgentMetricType;

import java.util.Map;

public interface MetricService
{
    void addLinuxAgentMetric(Long metricTypeId, Long storingPeriod, String cronString, Long agentId, LinuxAgentMetricType linuxAgentMetricType, Map<LinuxAgentMetricParam, Object> params);

    void addJmxAgentMetric(Long metricTypeId, Long storingPeriod, String cronString, Long agentId, String domainName, String objectName, String propertyName);
}
