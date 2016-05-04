package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.agent.AgentEntity;

import java.util.Collection;
import java.util.Optional;

public interface AgentDao
{
    Optional<AgentEntity> getById(Long agentEntityId);

    void delete(Long agentEntityId);

    AgentEntity save(AgentEntity agentEntity);

    Collection<AgentEntity> getAll();
}
