package com.sysmon.core.server.utils;

import com.sysmon.core.dal.entity.measure.BooleanMeasureEntity;
import com.sysmon.core.dal.entity.measure.DoubleMeasureEntity;
import com.sysmon.core.dal.entity.measure.FloatMeasureEntity;
import com.sysmon.core.dal.entity.measure.IntegerMeasureEntity;
import com.sysmon.core.dal.entity.measure.LongMeasureEntity;
import com.sysmon.core.dal.entity.measure.MeasureEntity;
import com.sysmon.core.server.measure.Measure;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class MeasureToMeasureEntityConverter implements Function<Measure, MeasureEntity>
{
    @Override
    public MeasureEntity apply(Measure measure)
    {
        Object value = measure.getValue();
        if (value instanceof Boolean) {
            return new BooleanMeasureEntity(measure.getMetric().getId(), measure.getDate(), (Boolean) value);
        }
        if (value instanceof Double) {
            return new DoubleMeasureEntity(measure.getMetric().getId(), measure.getDate(), (Double) value);
        }
        if (value instanceof Float) {
            return new FloatMeasureEntity(measure.getMetric().getId(), measure.getDate(), (Float) value);
        }
        if (value instanceof Long) {
            return new LongMeasureEntity(measure.getMetric().getId(), measure.getDate(), (Long) value);
        }
        if (value instanceof Integer) {
            return new IntegerMeasureEntity(measure.getMetric().getId(), measure.getDate(), (Integer) value);
        } else {
            throw new IllegalArgumentException("Unknown measure data type");
        }
    }
}
