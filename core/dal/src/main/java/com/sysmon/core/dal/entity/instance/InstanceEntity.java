package com.sysmon.core.dal.entity.instance;

import com.sysmon.core.dal.entity.agent.AgentEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "InstanceEntity.lazy",
                attributeNodes = {
                        @NamedAttributeNode("name")
                }
        ),
        @NamedEntityGraph(
                name = "InstanceEntity.detailed",
                attributeNodes = {
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode("agentList")
                }
        ),
        @NamedEntityGraph(
                name = "InstanceEntity.detailedWithAllChildren",
                attributeNodes = {
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode("agentList"),
                        @NamedAttributeNode("childList")
                }
        )
})
@Table(name = "INSTANCE")
public class InstanceEntity implements Serializable
{
    private static final long serialVersionUID = -7545643641092656943L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 128)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instanceEntity")
    private List<AgentEntity> agentEntityList;

    @ManyToMany
    @JoinTable(
            name = "INSTANCE_GROUP_INSTANCE",
            joinColumns = {@JoinColumn(name = "INSTANCE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "INSTANCE_GROUP_ID")}
    )
    private List<InstanceGroupEntity> instanceGroupEntities;

    @Override
    public String toString()
    {
        return "InstanceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", agentEntityList=" + agentEntityList +
                ", instanceGroupEntities=" + instanceGroupEntities +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstanceEntity that = (InstanceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<AgentEntity> getAgentEntityList()
    {
        return agentEntityList;
    }

    public void setAgentEntityList(List<AgentEntity> agentEntityList)
    {
        this.agentEntityList = agentEntityList;
    }

    public List<InstanceGroupEntity> getInstanceGroupEntities()
    {
        return instanceGroupEntities;
    }

    public void setInstanceGroupEntities(List<InstanceGroupEntity> instanceGroupEntities)
    {
        this.instanceGroupEntities = instanceGroupEntities;
    }
}
