package com.sysmon.core.server.metric.saver;

import com.sysmon.core.server.metric.Metric;

public interface MetricSaver
{
    void add(Metric metric);

    void remove(Metric metric);
}
