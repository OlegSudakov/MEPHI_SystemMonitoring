package com.sysmon.core.dal.entity.agent;

import com.sysmon.core.dal.entity.instance.InstanceEntity;
import com.sysmon.core.dal.entity.metric.CollectibleMetricEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "E_TYPE", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "AGENT")
public class AgentEntity implements Serializable
{
    private static final long serialVersionUID = 788139380490971378L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "INSTANCE_ID", referencedColumnName = "ID")
    protected InstanceEntity instanceEntity;

    @OneToMany(mappedBy = "agentEntity")
    protected List<CollectibleMetricEntity> collectibleMetricEntityList;

    @Override
    public String toString()
    {
        return "AgentEntity{" +
                "id=" + id +
                ", instanceEntity=" + instanceEntity +
                ", collectibleMetricEntityList=" + collectibleMetricEntityList +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgentEntity that = (AgentEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return instanceEntity != null ? instanceEntity.equals(that.instanceEntity) : that.instanceEntity == null;

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (instanceEntity != null ? instanceEntity.hashCode() : 0);
        return result;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public InstanceEntity getInstanceEntity()
    {
        return instanceEntity;
    }

    public void setInstanceEntity(InstanceEntity instanceEntity)
    {
        this.instanceEntity = instanceEntity;
    }

    public List<CollectibleMetricEntity> getCollectibleMetricEntityList()
    {
        return collectibleMetricEntityList;
    }

    public void setCollectibleMetricEntityList(List<CollectibleMetricEntity> collectibleMetricEntityList)
    {
        this.collectibleMetricEntityList = collectibleMetricEntityList;
    }
}
