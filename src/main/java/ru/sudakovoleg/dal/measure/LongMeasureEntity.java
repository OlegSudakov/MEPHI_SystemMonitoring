package ru.sudakovoleg.dal.measure;

import java.sql.Timestamp;

/**
 * Created by Oleg on 05.05.2016.
 */
public class LongMeasureEntity extends MeasureEntity{
    private Long value;

    public LongMeasureEntity(Long id, Timestamp time, Long value){
        super(id, time, value);
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
