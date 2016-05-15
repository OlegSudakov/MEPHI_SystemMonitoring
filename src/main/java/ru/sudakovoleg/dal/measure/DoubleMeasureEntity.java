package ru.sudakovoleg.dal.measure;

import java.sql.Timestamp;

/**
 * Created by Oleg on 05.05.2016.
 */
public class DoubleMeasureEntity extends MeasureEntity{
    private Double value;

    public DoubleMeasureEntity(Long id, Timestamp time, Double value){
        super(id, time, value);
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
