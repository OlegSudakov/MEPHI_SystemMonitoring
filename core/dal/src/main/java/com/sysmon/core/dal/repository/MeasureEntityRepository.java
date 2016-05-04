package com.sysmon.core.dal.repository;

import com.sysmon.core.dal.entity.measure.MeasureEntityPK;
import com.sysmon.core.dal.entity.measure.MeasureEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureEntityRepository extends CrudRepository<MeasureEntity, MeasureEntityPK>
{
}
