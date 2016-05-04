package com.sysmon.core.dal.repository;

import com.sysmon.core.dal.entity.metric.MetricTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricTypeEntityRepository extends CrudRepository<MetricTypeEntity, Long>
{
}
