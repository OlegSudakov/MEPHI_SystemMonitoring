package com.sysmon.core.dal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DATA_TYPE")
public class DataTypeEntity implements Serializable
{
    private static final long serialVersionUID = 8206821175673469664L;

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    public DataTypeEntity() {}

    public DataTypeEntity(
            Long id,
            String name
    )
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "DataTypeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof DataTypeEntity)) return false;

        DataTypeEntity dataTypeEntity = (DataTypeEntity) o;

        if (id != null ? !id.equals(dataTypeEntity.id) : dataTypeEntity.id != null) return false;
        return name != null ? name.equals(dataTypeEntity.name) : dataTypeEntity.name == null;
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
}
