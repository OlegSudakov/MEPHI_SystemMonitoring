package ru.sudakovoleg.dal.measure;

import java.sql.Timestamp;

/**
 * Created by Oleg on 05.05.2016.
 */
public class IntegerMeasureEntity extends MeasureEntity {
    private Integer value;

    public IntegerMeasureEntity(Long id, Timestamp time, Integer value){
        super(id, time, value);
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
