package com.sysmon.core.dal.entity.instance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "INSTANCE")
public class InstanceGroupEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true, length = 128)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "INSTANCE_GROUP_INSTANCE",
            joinColumns = {@JoinColumn(name = "INSTANCE_GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "INSTANCE_ID")}
    )
    private List<InstanceEntity> instanceEntities;

    @Override
    public String toString()
    {
        return "InstanceGroupEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instanceEntities=" + instanceEntities +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstanceGroupEntity that = (InstanceGroupEntity) o;

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

    public List<InstanceEntity> getInstanceEntities()
    {
        return instanceEntities;
    }

    public void setInstanceEntities(List<InstanceEntity> instanceEntities)
    {
        this.instanceEntities = instanceEntities;
    }
}
