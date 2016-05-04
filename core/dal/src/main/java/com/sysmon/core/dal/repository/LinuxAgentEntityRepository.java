package com.sysmon.core.dal.repository;

import com.sysmon.core.dal.entity.agent.LinuxAgentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinuxAgentEntityRepository extends CrudRepository<LinuxAgentEntity, Long>
{
}
