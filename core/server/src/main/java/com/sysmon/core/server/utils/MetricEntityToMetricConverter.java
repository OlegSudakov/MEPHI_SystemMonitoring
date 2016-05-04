package com.sysmon.core.server.utils;

import com.sysmon.core.dal.entity.metric.CalculableMetricEntity;
import com.sysmon.core.dal.entity.metric.CollectibleMetricEntity;
import com.sysmon.core.dal.entity.metric.MetricEntity;
import com.sysmon.core.server.metric.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MetricEntityToMetricConverter implements Function<MetricEntity, Metric>
{
    private final CollectibleMetricEntityToCollectibleMetricConverter collectibleMetricEntityToCollectibleMetricConverter;

    @Autowired
    public MetricEntityToMetricConverter(CollectibleMetricEntityToCollectibleMetricConverter collectibleMetricEntityToCollectibleMetricConverter)
    {
        this.collectibleMetricEntityToCollectibleMetricConverter = collectibleMetricEntityToCollectibleMetricConverter;
    }

    @Override
    public Metric apply(MetricEntity metricEntity)
    {
        if (metricEntity instanceof CollectibleMetricEntity) {
            return collectibleMetricEntityToCollectibleMetricConverter.apply((CollectibleMetricEntity) metricEntity);
        } else if (metricEntity instanceof CalculableMetricEntity) {
            return null;
        } else {
            throw new IllegalArgumentException("Unknown metric type enum");
        }
    }
}
