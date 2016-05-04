package com.sysmon.core.server.measure;

import com.sysmon.core.server.metric.Metric;

import java.util.Date;

public class Measure<T>
{
    protected final Date date;
    protected final T value;
    protected final Metric metric;

    public Measure(
            Date date,
            T value,
            Metric metric
    )
    {
        this.date = date;
        this.value = value;
        this.metric = metric;
    }

    public final Date getDate()
    {
        return date;
    }

    public final T getValue()
    {
        return value;
    }

    public final Metric getMetric()
    {
        return metric;
    }

    @Override
    public String toString()
    {
        return "Measure{" +
                "date=" + date +
                ", value=" + value +
                ", metric=" + metric +
                '}';
    }
}
