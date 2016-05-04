package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.instance.InstanceEntity;

import java.util.Optional;

public interface InstanceDao
{
    Optional<InstanceEntity> getInstanceByName(String name);

    Optional<InstanceEntity> getInstanceById(Long id);

    InstanceEntity save(InstanceEntity instanceEntity);
}
