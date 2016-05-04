package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.metric.MetricTypeEntity;

import java.util.Collection;
import java.util.Optional;

public interface MetricTypeDao
{
    Optional<MetricTypeEntity> getById(Long metricTypeEntityId);

    Collection<MetricTypeEntity> getAll();

    MetricTypeEntity save(MetricTypeEntity metricTypeEntity);
}
