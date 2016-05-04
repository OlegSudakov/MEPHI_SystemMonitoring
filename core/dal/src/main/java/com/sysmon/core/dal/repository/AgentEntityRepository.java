package com.sysmon.core.dal.repository;

import com.sysmon.core.dal.entity.agent.AgentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentEntityRepository extends CrudRepository<AgentEntity, Long>
{
}
