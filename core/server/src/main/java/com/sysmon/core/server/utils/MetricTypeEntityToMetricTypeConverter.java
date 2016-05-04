package com.sysmon.core.server.utils;

import com.sysmon.core.dal.entity.metric.MetricTypeEntity;
import com.sysmon.core.server.metric.MetricType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MetricTypeEntityToMetricTypeConverter implements Function<MetricTypeEntity, MetricType>
{
    private final DataTypeEntityToDataTypeConverter dataTypeEntityToDataTypeConverter;

    @Autowired
    public MetricTypeEntityToMetricTypeConverter(
            DataTypeEntityToDataTypeConverter dataTypeEntityToDataTypeConverter
    )
    {
        this.dataTypeEntityToDataTypeConverter = dataTypeEntityToDataTypeConverter;
    }

    @Override
    public MetricType apply(MetricTypeEntity metricTypeEntity)
    {
        return new MetricType(
                metricTypeEntity.getId(),
                metricTypeEntity.getName(),
                dataTypeEntityToDataTypeConverter.apply(metricTypeEntity.getDataTypeEntity())
        );
    }
}
