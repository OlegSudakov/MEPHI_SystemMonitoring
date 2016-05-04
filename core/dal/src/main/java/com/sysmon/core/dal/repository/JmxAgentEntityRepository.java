package com.sysmon.core.dal.repository;

import com.sysmon.core.dal.entity.agent.JmxAgentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JmxAgentEntityRepository extends CrudRepository<JmxAgentEntity, Long>
{
}
