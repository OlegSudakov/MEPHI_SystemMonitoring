package ru.sudakovoleg.dal.dao;

/**
 * Created by Oleg on 05.05.2016.
 */

import ru.sudakovoleg.dal.measure.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface EntityDAO {
    public ArrayList<BooleanMeasureEntity> getLastNBooleans(Long id, int num);
    public ArrayList<DoubleMeasureEntity> getLastNDoubles(Long id, int num);
    public ArrayList<FloatMeasureEntity> getLastNFloats(Long id, int num);
    public ArrayList<IntegerMeasureEntity> getLastNIntegers(Long id, int num);
    public ArrayList<LongMeasureEntity> getLastNLongs(Long id, int num);

    public ArrayList<BooleanMeasureEntity> getBooleanMeasuresFromPeriod(Long id, Timestamp start, Timestamp end);
    public ArrayList<DoubleMeasureEntity> getDoubleMeasuresFromPeriod(Long id, Timestamp start, Timestamp end);
    public ArrayList<FloatMeasureEntity> getFloatMeasuresFromPeriod(Long id, Timestamp start, Timestamp end);
    public ArrayList<IntegerMeasureEntity> getIntegerMeasuresFromPeriod(Long id, Timestamp start, Timestamp end);
    public ArrayList<LongMeasureEntity> getLongMeasuresFromPeriod(Long id, Timestamp start, Timestamp end);
}
