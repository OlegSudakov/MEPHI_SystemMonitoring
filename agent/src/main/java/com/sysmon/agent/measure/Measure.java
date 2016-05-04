package com.sysmon.agent.measure;

import java.io.Serializable;

public class Measure<T> implements Serializable
{
    protected long timestamp;
    protected T value;

    public Measure(
            long timestamp,
            T value
    )
    {
        this.timestamp = timestamp;
        this.value = value;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public T getValue()
    {
        return value;
    }
}
