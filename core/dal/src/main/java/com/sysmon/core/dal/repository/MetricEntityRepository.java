package com.sysmon.core.dal.repository;


import com.sysmon.core.dal.entity.metric.MetricEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricEntityRepository extends CrudRepository<MetricEntity, Long>
{
}
