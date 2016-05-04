package com.sysmon.core.dal.dao;

import com.sysmon.core.dal.entity.instance.InstanceEntity;
import com.sysmon.core.dal.repository.InstanceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class InstanceDaoImpl implements InstanceDao
{
    @PersistenceContext
    private EntityManager em;

    private InstanceEntityRepository instanceEntityRepository;

    @Autowired
    public InstanceDaoImpl(InstanceEntityRepository instanceEntityRepository)
    {
        this.instanceEntityRepository = instanceEntityRepository;
    }

    @Override
    public Optional<InstanceEntity> getInstanceByName(String name)
    {
        return Optional.ofNullable(instanceEntityRepository.findByName(name));

        //JPA 1.0 style (JPQL)
//        Query query = em.createQuery("select s from Instance s where s.name = :name")
//                .setParameter("name", name);
//        List resultList = query.getResultList();
//        return resultList.isEmpty() ? null : (GetInstance.Instance) resultList.get(0);

        //JPA 2.0 style
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Instance> cq = cb.createQuery(Instance.class);
//        Root<Instance> s = cq.from(Instance.class);
//        Predicate condition = cb.equal(s.get(Instance_.name), name);
//        cq.where(condition);
//        TypedQuery<Instance> q = em.createQuery(cq);
//        return q.getSingleResult();

        //querydsl style
//        QInstance instances = QInstance.server;
//        JPAQuery<Instance> query = new JPAQuery<>(em);
//        return query.from(instances)
//                .where(instances.name.eq(name))
//                .fetchFirst();
    }

    @Override
    public Optional<InstanceEntity> getInstanceById(Long id)
    {
        return Optional.ofNullable(instanceEntityRepository.findById(id));
    }

    @Override
    public InstanceEntity save(InstanceEntity instanceEntity)
    {
        return instanceEntityRepository.save(instanceEntity);
    }
}
