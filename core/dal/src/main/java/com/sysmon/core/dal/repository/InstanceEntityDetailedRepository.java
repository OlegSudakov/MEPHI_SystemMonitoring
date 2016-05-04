package com.sysmon.core.dal.repository;

import com.sysmon.core.dal.entity.instance.InstanceEntity;
import org.springframework.data.jpa.repository.EntityGraph;

public interface InstanceEntityDetailedRepository
{
    @EntityGraph(value = "Instance.detailed", type = EntityGraph.EntityGraphType.LOAD)
    InstanceEntity findById(Long id);

    @EntityGraph(value = "Instance.detailed", type = EntityGraph.EntityGraphType.LOAD)
    InstanceEntity findByName(String name);
}
