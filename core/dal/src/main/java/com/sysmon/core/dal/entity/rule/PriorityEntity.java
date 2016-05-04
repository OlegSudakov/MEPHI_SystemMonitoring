package com.sysmon.core.dal.entity.rule;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRIORITY")
public class PriorityEntity implements Serializable
{
    private static final long serialVersionUID = -8381393455245823229L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true, length = 128)
    private String name;

    @Override
    public String toString()
    {
        return "PriorityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PriorityEntity)) return false;

        PriorityEntity priorityEntity = (PriorityEntity) o;

        if (getId() != null ? !getId().equals(priorityEntity.getId()) : priorityEntity.getId() != null) return false;
        return getName() != null ? getName().equals(priorityEntity.getName()) : priorityEntity.getName() == null;
    }

    @Override
    public int hashCode()
    {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
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
}
