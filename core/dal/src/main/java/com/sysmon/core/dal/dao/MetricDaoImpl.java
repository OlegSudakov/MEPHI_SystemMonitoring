package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.metric.MetricEntity;
import com.sysmon.core.dal.repository.MetricEntityRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class MetricDaoImpl implements MetricDao
{
    private MetricEntityRepository metricEntityRepository;

    @Autowired
    public MetricDaoImpl(MetricEntityRepository metricEntityRepository)
    {
        this.metricEntityRepository = metricEntityRepository;
    }

    @Override
    public Optional<MetricEntity> getById(Long id)
    {
        return Optional.ofNullable(metricEntityRepository.findOne(id));
    }

    @Override
    public Collection<MetricEntity> getAll()
    {
        return Lists.newArrayList(metricEntityRepository.findAll());
    }

    @Override
    public MetricEntity save(MetricEntity metricEntity)
    {
        return metricEntityRepository.save(metricEntity);
    }
}
