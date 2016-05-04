package com.sysmon.core.server.utils;

import com.sysmon.core.dal.entity.DataTypeEntity;
import com.sysmon.core.server.metric.DataType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;

@Component
public class DataTypeEntityToDataTypeConverter implements Function<DataTypeEntity, DataType>
{
    @Override
    public DataType apply(DataTypeEntity dataTypeEntity)
    {
        return Arrays.asList(DataType.values()).stream()
                .parallel()
                .filter(dtype -> dtype.ordinal() == dataTypeEntity.getId())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown data type"));
    }
}
