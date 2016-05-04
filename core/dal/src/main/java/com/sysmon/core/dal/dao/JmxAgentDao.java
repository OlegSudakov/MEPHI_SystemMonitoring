package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.agent.JmxAgentEntity;

import java.util.Optional;

public interface JmxAgentDao
{
    JmxAgentEntity save(JmxAgentEntity jmxAgentEntity);

    Optional<JmxAgentEntity> getById(Long jmxAgentEntityId);
}
