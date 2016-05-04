package com.sysmon.core.dal.entity.formula;

import com.sysmon.core.dal.entity.metric.CalculableMetricEntity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "METRIC_FORMULA")
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class MetricFormulaEntity extends FormulaEntity
{
    private static final long serialVersionUID = -7893163012708882248L;

    @OneToOne(mappedBy = "metricFormula")
    private CalculableMetricEntity calculableMetric;

    @Override
    public String toString()
    {
        return "MetricFormulaEntity{" +
                "id=" + id +
                ", formula=" + Arrays.toString(formula) +
                ", metricEntityList=" + metricEntityList +
                ", calculableMetric=" + calculableMetric +
                '}';
    }

    public CalculableMetricEntity getCalculableMetric()
    {
        return calculableMetric;
    }

    public void setCalculableMetric(CalculableMetricEntity calculableMetric)
    {
        this.calculableMetric = calculableMetric;
    }
}
