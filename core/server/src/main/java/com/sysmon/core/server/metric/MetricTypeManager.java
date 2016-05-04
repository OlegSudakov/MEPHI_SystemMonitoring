package com.sysmon.core.server.metric;

import java.util.Optional;

public interface MetricTypeManager
{
    Optional<MetricType> getById(Long id);

    void add(MetricType metricType);
}
