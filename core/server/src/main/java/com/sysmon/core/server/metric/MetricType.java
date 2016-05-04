package com.sysmon.core.server.metric;

public class MetricType
{
    protected final Long id;
    protected final String name;
    protected final DataType dataType;

    public MetricType(
            Long id,
            String name,
            DataType dataType
    )
    {
        this.id = id;
        this.name = name;
        this.dataType = dataType;
    }

    @Override
    public String toString()
    {
        return "MetricType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dataType=" + dataType +
                '}';
    }

    public final Long getId()
    {
        return id;
    }

    public final String getName()
    {
        return name;
    }

    public final DataType getDataType()
    {
        return dataType;
    }
}
