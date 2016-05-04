package com.sysmon.core.dal.entity.measure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "BOOLEAN_MEASURE")
public class BooleanMeasureEntity extends MeasureEntity
{
    private static final long serialVersionUID = 2228545984986055183L;

    @Column(name = "VALUE")
    private Boolean value;

    public BooleanMeasureEntity() {}

    public BooleanMeasureEntity(
            MeasureEntityPK id,
            Boolean value
    )
    {
        super(id);
        this.value = value;
    }

    public BooleanMeasureEntity(
            Long metricId,
            Date time,
            Boolean value
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

    public Boolean getValue()
    {
        return value;
    }

    public void setValue(Boolean value)
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
