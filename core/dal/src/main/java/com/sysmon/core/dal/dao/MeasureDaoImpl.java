package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.measure.MeasureEntity;
import com.sysmon.core.dal.repository.MeasureEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class MeasureDaoImpl implements MeasureDao
{
    private MeasureEntityRepository measureEntityRepository;

    @Autowired
    public MeasureDaoImpl(MeasureEntityRepository measureEntityRepository)
    {
        this.measureEntityRepository = measureEntityRepository;
    }

    @Override
    public void save(Collection<MeasureEntity> measureEntities)
    {
        measureEntityRepository.save(measureEntities);
    }
}
