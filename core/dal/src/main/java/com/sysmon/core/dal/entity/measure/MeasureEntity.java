package com.sysmon.core.dal.entity.measure;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MeasureEntity implements Serializable
{
    private static final long serialVersionUID = -8677807815550498307L;

    @EmbeddedId
    protected MeasureEntityPK id;

    public MeasureEntity() {}

    public MeasureEntity(MeasureEntityPK id)
    {
        this.id = id;
    }

    public MeasureEntity(
            Long metricId,
            Date time
    )
    {
        this(new MeasureEntityPK(metricId, time));
    }

    public MeasureEntityPK getId()
    {
        return id;
    }

    public void setId(MeasureEntityPK id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "MeasureEntity{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasureEntity measureEntity = (MeasureEntity) o;

        return id.equals(measureEntity.id);

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? (int) (id.getMetricId() ^ (id.getMetricId() >>> 32)) : 0;
        return result;
    }

}
