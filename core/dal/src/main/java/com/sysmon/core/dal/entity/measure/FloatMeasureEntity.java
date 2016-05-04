package com.sysmon.core.dal.entity.measure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "FLOAT_MEASURE")
public class FloatMeasureEntity extends MeasureEntity
{
    private static final long serialVersionUID = 5240216900038499402L;

    @Column(name = "VALUE")
    private Float value;

    public FloatMeasureEntity() {}

    public FloatMeasureEntity(
            MeasureEntityPK id,
            Float value
    )
    {
        super(id);
        this.value = value;
    }

    public FloatMeasureEntity(
            Long metricId,
            Date time,
            Float value
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

    public Float getValue()
    {
        return value;
    }

    public void setValue(Float value)
    {
        this.value = value;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof FloatMeasureEntity)) return false;
        if (!super.equals(o)) return false;

        FloatMeasureEntity that = (FloatMeasureEntity) o;

        return getValue() != null ? getValue().equals(that.getValue()) : that.getValue() == null;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
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
