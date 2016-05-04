package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.metric.MetricEntity;

import java.util.Collection;
import java.util.Optional;

public interface MetricDao
{
    Optional<MetricEntity> getById(Long id);

    Collection<MetricEntity> getAll();

    MetricEntity save(MetricEntity metricEntity);
}
