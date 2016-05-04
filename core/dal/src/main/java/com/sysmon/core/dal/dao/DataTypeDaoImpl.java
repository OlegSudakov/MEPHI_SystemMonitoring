package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.DataTypeEntity;
import com.sysmon.core.dal.repository.DataTypeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DataTypeDaoImpl implements DataTypeDao
{
    private final DataTypeEntityRepository dataTypeEntityRepository;

    @Autowired
    public DataTypeDaoImpl(DataTypeEntityRepository dataTypeEntityRepository)
    {
        this.dataTypeEntityRepository = dataTypeEntityRepository;
    }

    @Override
    public Optional<DataTypeEntity> getById(Long id)
    {
        return Optional.ofNullable(dataTypeEntityRepository.findOne(id));
    }
}
