package com.sysmon.core.dal.entity.measure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DOUBLE_MEASURE")
public class DoubleMeasureEntity extends MeasureEntity
{
    private static final long serialVersionUID = -1400756019623875413L;

    @Column(name = "VALUE")
    private Double value;

    public DoubleMeasureEntity() {}

    public DoubleMeasureEntity(
            MeasureEntityPK id,
            Double value
    )
    {
        super(id);
        this.value = value;
    }

    public DoubleMeasureEntity(
            Long metricId,
            Date time,
            Double value
    )
    {
        super(metricId, time);
        this.value = value;
    }

    public MeasureEntityPK getId()
    {
        return id;
    }

    public void setId(MeasureEntityPK id)
    {
        this.id = id;
    }

    public Double getValue()
    {
        return value;
    }

    public void setValue(Double value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "LongMeasureEntity{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
