package com.sysmon.core.dal.entity.measure;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class MeasureEntityPK implements Serializable
{
    @Column(name = "METRIC_ID")
    private Long metricId;

    @Column(name = "TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public MeasureEntityPK() {}

    public MeasureEntityPK(
            Long metricId,
            Date timestamp
    )
    {
        this.metricId = metricId;
        this.timestamp = timestamp;
    }

    public Long getMetricId()
    {
        return metricId;
    }

    public void setMetricId(Long seriesID)
    {
        this.metricId = seriesID;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasureEntityPK measureEntityPK = (MeasureEntityPK) o;

        return metricId.equals(measureEntityPK.getMetricId()) && timestamp.equals(measureEntityPK.timestamp);
    }

    @Override
    public String toString()
    {
        return metricId + ";" + timestamp;
    }
}
