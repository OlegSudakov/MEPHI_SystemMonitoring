package com.sysmon.core.dal.dao;

import com.google.common.collect.Lists;
import com.sysmon.core.dal.entity.agent.AgentEntity;
import com.sysmon.core.dal.repository.AgentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class AgentDaoImpl implements AgentDao
{
    private final AgentEntityRepository agentEntityRepository;

    @Autowired
    public AgentDaoImpl(AgentEntityRepository agentEntityRepository)
    {
        this.agentEntityRepository = agentEntityRepository;
    }

    @Override
    public Optional<AgentEntity> getById(Long agentEntityId)
    {
        return Optional.ofNullable(agentEntityRepository.findOne(agentEntityId));
    }

    @Override
    public void delete(Long agentEntityId)
    {
        agentEntityRepository.delete(agentEntityId);
    }

    @Override
    public AgentEntity save(AgentEntity agentEntity)
    {
        return agentEntityRepository.save(agentEntity);
    }

    @Override
    public Collection<AgentEntity> getAll()
    {
        return Lists.newArrayList(agentEntityRepository.findAll());
    }
}
