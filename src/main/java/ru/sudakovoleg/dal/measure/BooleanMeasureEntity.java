package ru.sudakovoleg.dal.measure;

import java.sql.Timestamp;

/**
 * Created by Oleg on 05.05.2016.
 */
public class BooleanMeasureEntity extends MeasureEntity{
    private Boolean value;

    public BooleanMeasureEntity(Long id, Timestamp time, Boolean value){
        super(id, time, value);
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
