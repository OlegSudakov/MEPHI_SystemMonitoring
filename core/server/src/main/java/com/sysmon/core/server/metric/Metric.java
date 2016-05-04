package com.sysmon.core.server.metric;

public class Metric
{
    protected final Long id;
    protected final MetricTypeEnum metricTypeEnum;
    protected final MetricType metricType;
    protected final Long storingPeriod;
    protected final String cronString;

    public Metric(
            Long id,
            MetricTypeEnum metricTypeEnum,
            final MetricType metricType,
            Long storingPeriod,
            String cronString
    )
    {
        if (id == null) {
            throw new IllegalArgumentException("Metric id can not be null");
        }
        if (metricTypeEnum == null) {
            throw new IllegalArgumentException("Metric type enum can not be null");
        }
        if (metricType == null) {
            throw new IllegalArgumentException("Metric type can not be null");
        }
        this.id = id;
        this.metricTypeEnum = metricTypeEnum;
        this.metricType = metricType;
        this.storingPeriod = storingPeriod;
        this.cronString = cronString;
    }

    @Override
    public String toString()
    {
        return "Metric{" +
                "id=" + id +
                ", metricTypeEnum=" + metricTypeEnum +
                ", metricType=" + metricType +
                ", storingPeriod=" + storingPeriod +
                ", cronString='" + cronString + '\'' +
                '}';
    }

    public final Long getId()
    {
        return id;
    }

    public final MetricTypeEnum getMetricTypeEnum()
    {
        return metricTypeEnum;
    }

    public final MetricType getMetricType()
    {
        return metricType;
    }

    public final Long getStoringPeriod()
    {
        return storingPeriod;
    }

    public final String getCronString()
    {
        return cronString;
    }
}
