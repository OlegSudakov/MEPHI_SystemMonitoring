package ru.sudakovoleg.dal.measure;

import java.sql.Timestamp;

/**
 * Created by Oleg on 05.05.2016.
 */
public class FloatMeasureEntity extends MeasureEntity {
    private Float value;

    public FloatMeasureEntity(Long id, Timestamp time, Float value){
        super(id, time, value);
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
