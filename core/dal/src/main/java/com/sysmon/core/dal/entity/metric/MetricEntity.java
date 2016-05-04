package com.sysmon.core.dal.entity.metric;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "E_TYPE", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "METRIC")
public class MetricEntity implements Serializable
{
    private static final long serialVersionUID = -1623059854537922846L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "METRIC_TYPE_ID", referencedColumnName = "ID")
    protected MetricTypeEntity metricTypeEntity;

    @Column(name = "STORING_PERIOD")
    protected Long storingPeriod;

    @Column(name = "CRON", length = 256)
    protected String cron;

    public MetricEntity() {}

    public MetricEntity(
            MetricTypeEntity metricTypeEntity,
            String cron
    )
    {
        this.metricTypeEntity = metricTypeEntity;
        this.cron = cron;
    }

    @Override
    public String toString()
    {
        return "Metric{" +
                "id=" + id +
                ", metricType=" + metricTypeEntity +
                ", storingPeriod=" + storingPeriod +
                ", cron='" + cron + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof MetricEntity)) return false;

        MetricEntity metricEntity = (MetricEntity) o;

        if (id != null ? !id.equals(metricEntity.id) : metricEntity.id != null) return false;
        if (metricTypeEntity != null ? !metricTypeEntity.equals(metricEntity.metricTypeEntity) : metricEntity.metricTypeEntity != null)
            return false;
        if (storingPeriod != null ? !storingPeriod.equals(metricEntity.storingPeriod) : metricEntity.storingPeriod != null)
            return false;
        return cron != null ? cron.equals(metricEntity.cron) : metricEntity.cron == null;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (metricTypeEntity != null ? metricTypeEntity.hashCode() : 0);
        result = 31 * result + (storingPeriod != null ? storingPeriod.hashCode() : 0);
        result = 31 * result + (cron != null ? cron.hashCode() : 0);
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

    public MetricTypeEntity getMetricTypeEntity()
    {
        return metricTypeEntity;
    }

    public void setMetricTypeEntity(MetricTypeEntity metricTypeEntity)
    {
        this.metricTypeEntity = metricTypeEntity;
    }

    public Long getStoringPeriod()
    {
        return storingPeriod;
    }

    public void setStoringPeriod(Long storingPeriod)
    {
        this.storingPeriod = storingPeriod;
    }

    public String getCron()
    {
        return cron;
    }

    public void setCron(String cron)
    {
        this.cron = cron;
    }
}
