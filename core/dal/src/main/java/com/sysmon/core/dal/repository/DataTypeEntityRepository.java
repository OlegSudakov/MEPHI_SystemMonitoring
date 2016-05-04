package com.sysmon.core.dal.repository;

import com.sysmon.core.dal.entity.DataTypeEntity;
import com.sysmon.core.dal.entity.metric.MetricTypeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTypeEntityRepository extends ImmutableRepository<DataTypeEntity, Long>
{
}
