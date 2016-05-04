package com.sysmon.core.dal.repository;

import com.sysmon.core.dal.entity.instance.InstanceEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstanceEntityRepository extends CrudRepository<InstanceEntity, Long> {
    @EntityGraph("Instance.lazy")
    InstanceEntity findById(Long id);

    @EntityGraph("Instance.lazy")
    InstanceEntity findByName(String name);

    @EntityGraph("Instance.lazy")
    List<InstanceEntity> findAll();

    @EntityGraph("Instance.lazy")
    void deleteByName(String name);
}
