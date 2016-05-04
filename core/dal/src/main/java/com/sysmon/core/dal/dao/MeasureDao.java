package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.measure.MeasureEntity;

import java.util.Collection;

public interface MeasureDao
{
    void save(Collection<MeasureEntity> measureEntities);
}
