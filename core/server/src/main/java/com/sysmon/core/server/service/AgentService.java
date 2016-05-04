package com.sysmon.core.server.service;

public interface AgentService
{
    void addLinuxAgent(Long instanceId, String host, Integer port);

    void updateLinuxAgent(Long agentId, Long instanceId, String host, Integer port);

    void addJmxAgent(Long instanceId, String host, Integer port);

    void updateJmxAgent(Long agentId, Long instanceId, String host, Integer port);

    void removeAgent(Long agentId);
}
