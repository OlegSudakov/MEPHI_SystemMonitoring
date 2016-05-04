package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.agent.LinuxAgentEntity;

import java.util.Optional;

public interface LinuxAgentDao
{
    LinuxAgentEntity save(LinuxAgentEntity linuxAgentEntity);

    Optional<LinuxAgentEntity> getById(Long linuxAgentEntityId);
}
