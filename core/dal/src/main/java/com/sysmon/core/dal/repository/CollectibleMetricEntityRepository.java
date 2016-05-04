package com.sysmon.core.dal.repository;

import com.sysmon.core.dal.entity.metric.CollectibleMetricEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectibleMetricEntityRepository extends CrudRepository<CollectibleMetricEntity, Long>
{
}
