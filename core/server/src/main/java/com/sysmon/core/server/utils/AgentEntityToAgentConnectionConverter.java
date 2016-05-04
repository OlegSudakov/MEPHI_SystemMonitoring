package com.sysmon.core.server.utils;

import com.sysmon.core.dal.entity.agent.AgentEntity;
import com.sysmon.core.dal.entity.agent.JmxAgentEntity;
import com.sysmon.core.dal.entity.agent.LinuxAgentEntity;
import com.sysmon.core.server.agent.AgentConnection;
import com.sysmon.core.server.agent.jmx.JmxAgentConnection;
import com.sysmon.core.server.agent.linux.LinuxAgentConnection;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AgentEntityToAgentConnectionConverter implements Function<AgentEntity, AgentConnection>
{
    @Override
    public AgentConnection apply(AgentEntity agentEntity)
    {
        AgentConnection agentConnection = null;
        if (agentEntity instanceof LinuxAgentEntity) {
            LinuxAgentEntity linuxAgentEntity = (LinuxAgentEntity) agentEntity;
            agentConnection = new LinuxAgentConnection(
                    linuxAgentEntity.getId(),
                    linuxAgentEntity.getHost(),
                    linuxAgentEntity.getPort()
            );
        } else if (agentEntity instanceof JmxAgentEntity) {
            JmxAgentEntity jmxAgentEntity = (JmxAgentEntity) agentEntity;
            agentConnection = new JmxAgentConnection(
                    jmxAgentEntity.getId(),
                    jmxAgentEntity.getHost(),
                    jmxAgentEntity.getPort()
            );
        }
        if (agentConnection == null) {
            throw new IllegalArgumentException("Unknown agent type");
        }
        return agentConnection;
    }
}
