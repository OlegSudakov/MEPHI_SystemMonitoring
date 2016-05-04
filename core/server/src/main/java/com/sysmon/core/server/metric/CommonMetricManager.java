package com.sysmon.core.server.metric;

import java.util.Collection;
import java.util.Optional;

public interface CommonMetricManager extends MetricManager<Metric>, MetricRequestHandler<Metric>
{
    Collection<Metric> getMetrics(MetricTypeEnum metricTypeEnum);

    Optional<? extends Metric> getMetrics(MetricTypeEnum metricTypeEnum, Long id);
}
