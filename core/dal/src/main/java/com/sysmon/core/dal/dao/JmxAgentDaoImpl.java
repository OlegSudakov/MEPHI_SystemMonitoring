package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.agent.JmxAgentEntity;
import com.sysmon.core.dal.repository.JmxAgentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JmxAgentDaoImpl implements JmxAgentDao
{
    private JmxAgentEntityRepository jmxAgentEntityRepository;

    @Autowired
    public JmxAgentDaoImpl(JmxAgentEntityRepository jmxAgentEntityRepository)
    {
        this.jmxAgentEntityRepository = jmxAgentEntityRepository;
    }

    @Override
    public JmxAgentEntity save(JmxAgentEntity jmxAgentEntity)
    {
        return jmxAgentEntityRepository.save(jmxAgentEntity);
    }

    @Override
    public Optional<JmxAgentEntity> getById(Long jmxAgentEntityId)
    {
        return Optional.ofNullable(jmxAgentEntityRepository.findOne(jmxAgentEntityId));
    }
}
