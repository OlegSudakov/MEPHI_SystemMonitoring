package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.agent.LinuxAgentEntity;
import com.sysmon.core.dal.repository.LinuxAgentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LinuxAgentDaoImpl implements LinuxAgentDao
{
    private LinuxAgentEntityRepository linuxAgentEntityRepository;

    @Autowired
    public LinuxAgentDaoImpl(LinuxAgentEntityRepository linuxAgentEntityRepository)
    {
        this.linuxAgentEntityRepository = linuxAgentEntityRepository;
    }

    @Override
    public LinuxAgentEntity save(LinuxAgentEntity linuxAgentEntity)
    {
        return linuxAgentEntityRepository.save(linuxAgentEntity);
    }

    @Override
    public Optional<LinuxAgentEntity> getById(Long linuxAgentEntityId)
    {
        return Optional.ofNullable(linuxAgentEntityRepository.findOne(linuxAgentEntityId));
    }
}
