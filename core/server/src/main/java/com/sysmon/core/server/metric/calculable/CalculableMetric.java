package com.sysmon.core.server.metric.calculable;

import com.sysmon.core.server.metric.Metric;
import com.sysmon.core.server.metric.MetricType;
import com.sysmon.core.server.metric.MetricTypeEnum;

public class CalculableMetric extends Metric
{
    public CalculableMetric(
            Long id,
            MetricTypeEnum metricTypeEnum,
            MetricType metricType,
            Long storingPeriod,
            String cron
    )
    {
        super(id, metricTypeEnum, metricType, storingPeriod, cron);
    }

    @Override
    public String toString()
    {
        return "CollectibleMetric{" +
                "id=" + id +
                ", metricTypeEnum=" + metricTypeEnum +
                ", metricType=" + metricType +
                ", storingPeriod=" + storingPeriod +
                ", cronString='" + cronString + '\'' +
                '}';
    }
}
