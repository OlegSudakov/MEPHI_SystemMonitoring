package com.sysmon.core.dal.entity.measure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "LONG_MEASURE")
public class LongMeasureEntity extends MeasureEntity
{
    private static final long serialVersionUID = 1194906323963319507L;

    @Column(name = "VALUE")
    private Long value;

    public LongMeasureEntity() {}

    public LongMeasureEntity(
            MeasureEntityPK id,
            Long value
    )
    {
        super(id);
        this.value = value;
    }

    public LongMeasureEntity(
            Long metricId,
            Date time,
            Long value
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

    public Long getValue()
    {
        return value;
    }

    public void setValue(Long value)
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
