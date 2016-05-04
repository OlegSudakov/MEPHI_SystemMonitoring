package com.sysmon.core.dal.entity.metric;

import com.sysmon.core.dal.entity.instance.InstanceEntity;
import com.sysmon.core.dal.entity.formula.MetricFormulaEntity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "CALCULABLE_METRIC")
public class CalculableMetricEntity extends MetricEntity
{
    private static final long serialVersionUID = -229003817008649642L;

    @OneToOne
    @JoinColumn(name = "METRIC_FORMULA_ID", referencedColumnName = "ID", updatable = false, insertable = false)
    private MetricFormulaEntity metricFormula;

    @ManyToOne
    @JoinColumn(name = "METRIC_FORMULA_ID", referencedColumnName = "ID")
    private InstanceEntity instanceEntity;

    @Override
    public String toString()
    {
        return "CalculableMetricEntity{" +
                "id=" + id +
                ", metricType=" + metricTypeEntity +
                ", storingPeriod=" + storingPeriod +
                ", cron='" + cron + '\'' +
                ", metricFormula=" + metricFormula +
                ", instanceEntity=" + instanceEntity +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof CalculableMetricEntity)) return false;
        if (!super.equals(o)) return false;

        CalculableMetricEntity that = (CalculableMetricEntity) o;

        if (getMetricFormula() != null ? !getMetricFormula().equals(that.getMetricFormula()) : that.getMetricFormula() != null)
            return false;
        return getInstanceEntity() != null ? getInstanceEntity().equals(that.getInstanceEntity()) : that.getInstanceEntity() == null;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (getMetricFormula() != null ? getMetricFormula().hashCode() : 0);
        result = 31 * result + (getInstanceEntity() != null ? getInstanceEntity().hashCode() : 0);
        return result;
    }

    public MetricFormulaEntity getMetricFormula()
    {
        return metricFormula;
    }

    public void setMetricFormula(MetricFormulaEntity metricFormula)
    {
        this.metricFormula = metricFormula;
    }

    public InstanceEntity getInstanceEntity()
    {
        return instanceEntity;
    }

    public void setInstanceEntity(InstanceEntity instanceEntity)
    {
        this.instanceEntity = instanceEntity;
    }
}
