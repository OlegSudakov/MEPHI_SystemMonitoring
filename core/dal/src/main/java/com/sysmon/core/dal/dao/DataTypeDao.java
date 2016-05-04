package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.DataTypeEntity;

import java.util.Optional;

public interface DataTypeDao
{
    Optional<DataTypeEntity> getById(Long id);
}
