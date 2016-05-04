package com.sysmon.core.dal.entity.formula;

import com.sysmon.core.dal.entity.metric.MetricEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "E_TYPE", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "FORMULA")
public class FormulaEntity implements Serializable
{
    private static final long serialVersionUID = 3845670076800266946L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected Long id;

    @Column(name = "FORMULA", nullable = false)
    @Lob
    protected byte[] formula;

    @ManyToMany
    @JoinTable(
            name = "FORMULA_METRIC",
            joinColumns = {@JoinColumn(name = "FORMULA_ID")},
            inverseJoinColumns = {@JoinColumn(name = "METRIC_ID")}
    )
    protected List<MetricEntity> metricEntityList;

    @Override
    public String toString()
    {
        return "FormulaEntity{" +
                "id=" + id +
                ", formula=" + Arrays.toString(formula) +
                ", metricEntityList=" + metricEntityList +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormulaEntity that = (FormulaEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return Arrays.equals(formula, that.formula);

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(formula);
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

    public byte[] getFormula()
    {
        return formula;
    }

    public void setFormula(byte[] formula)
    {
        this.formula = formula;
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
