package com.sysmon.core.dal.entity.measure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "INTEGER_MEASURE")
public class IntegerMeasureEntity extends MeasureEntity
{
    private static final long serialVersionUID = 9196478090114983791L;

    @Column(name = "VALUE")
    private Integer value;

    public IntegerMeasureEntity() {}

    public IntegerMeasureEntity(
            MeasureEntityPK id,
            Integer value
    )
    {
        super(id);
        this.value = value;
    }

    public IntegerMeasureEntity(
            Long metricId,
            Date time,
            Integer value
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
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
