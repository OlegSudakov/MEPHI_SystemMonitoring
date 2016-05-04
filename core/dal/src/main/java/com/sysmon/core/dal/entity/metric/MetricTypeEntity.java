package com.sysmon.core.dal.entity.metric;

import com.sysmon.core.dal.entity.DataTypeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "MetricTypeEntity.lazy",
                attributeNodes = {
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode("dataTypeEntity")
                }
        ),
        @NamedEntityGraph(
                name = "MetricTypeEntity.detailed",
                attributeNodes = {
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode("dataTypeEntity"),
                        @NamedAttributeNode("metricList")
                }
        )
})
@Table(name = "METRIC_TYPE")
public class MetricTypeEntity implements Serializable
{
    private static final long serialVersionUID = 6767005585207049591L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 128)
    private String name;

    @ManyToOne
    @JoinColumn(name = "DATA_TYPE_ID", referencedColumnName = "ID")
    private DataTypeEntity dataTypeEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "metricTypeEntity")
    private List<MetricEntity> metricEntityList;

    @Override
    public String toString()
    {
        return "MetricTypeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dataTypeEntity=" + dataTypeEntity +
                ", metricEntityList=" + metricEntityList +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof MetricTypeEntity)) return false;

        MetricTypeEntity that = (MetricTypeEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (dataTypeEntity != null ? !dataTypeEntity.equals(that.dataTypeEntity) : that.dataTypeEntity != null)
            return false;
        return getMetricEntityList() != null ? getMetricEntityList().equals(that.getMetricEntityList()) : that.getMetricEntityList() == null;
    }

    @Override
    public int hashCode()
    {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (dataTypeEntity != null ? dataTypeEntity.hashCode() : 0);
        result = 31 * result + (getMetricEntityList() != null ? getMetricEntityList().hashCode() : 0);
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

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public DataTypeEntity getDataTypeEntity()
    {
        return dataTypeEntity;
    }

    public void setDataTypeEntity(DataTypeEntity dataTypeEntity)
    {
        this.dataTypeEntity = dataTypeEntity;
    }

    public List<MetricEntity> getMetricEntityList()
    {
        return metricEntityList;
    }

    public void setMetricEntityList(List<MetricEntity> metricEntityList)
    {
        this.metricEntityList = metricEntityList;
    }
}
