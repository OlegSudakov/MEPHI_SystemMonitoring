package com.sysmon.core.dal.dao;

import com.google.common.collect.Lists;
import com.sysmon.core.dal.entity.metric.MetricTypeEntity;
import com.sysmon.core.dal.repository.MetricTypeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class MetricTypeDaoImpl implements MetricTypeDao
{
    private MetricTypeEntityRepository metricTypeEntityRepository;

    @Autowired
    public MetricTypeDaoImpl(MetricTypeEntityRepository metricTypeEntityRepository)
    {
        this.metricTypeEntityRepository = metricTypeEntityRepository;
    }

    @Override
    public Optional<MetricTypeEntity> getById(Long metricTypeEntityId)
    {
        return Optional.ofNullable(metricTypeEntityRepository.findOne(metricTypeEntityId));
    }

    @Override
    public Collection<MetricTypeEntity> getAll()
    {
        return Lists.newArrayList(metricTypeEntityRepository.findAll());
    }

    @Override
    public MetricTypeEntity save(MetricTypeEntity metricTypeEntity)
    {
        return metricTypeEntityRepository.save(metricTypeEntity);
    }
}
