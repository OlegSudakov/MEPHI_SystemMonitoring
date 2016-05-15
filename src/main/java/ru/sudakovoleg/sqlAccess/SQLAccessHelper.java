package ru.sudakovoleg.sqlAccess;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ru.sudakovoleg.dal.dao.EntityDAO;
import ru.sudakovoleg.dal.dao.MetricDAO;
import ru.sudakovoleg.dal.measure.*;
import ru.sudakovoleg.dal.metric.MetricEntity;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Oleg on 07.05.2016.
 */
public class SQLAccessHelper {

    /* Data type codes:
    1 - Integer
    2 - Long
    3 - Float
    4 - Double
    5 - Boolean
    */

    public ArrayList<MeasureEntity> getLastNMeasures(Long id, int num){
        ApplicationContext context = new FileSystemXmlApplicationContext(
                "C:/Users/Oleg/IdeaProjects/SystemMonitoring/web/resources/Spring-Module.xml");
        EntityDAO entityDAO = (EntityDAO) context.getBean("entityDAO");
        Long dataTypeId = getDataTypeById(id);
        ArrayList<MeasureEntity> measures = new ArrayList<MeasureEntity>();
        switch(dataTypeId.intValue()) {
            case 1:
                ArrayList<IntegerMeasureEntity> intMeasures = entityDAO.getLastNIntegers(id, num);
                for (IntegerMeasureEntity intMeasure : intMeasures) {
                    measures.add(intMeasure);
                }
                break;
            case 2:
                ArrayList<LongMeasureEntity> longMeasures = entityDAO.getLastNLongs(id, num);
                for (LongMeasureEntity longMeasure : longMeasures) {
                    measures.add(longMeasure);
                }
                break;
            case 3:
                ArrayList<FloatMeasureEntity> floatMeasures = entityDAO.getLastNFloats(id, num);
                for (FloatMeasureEntity floatMeasure : floatMeasures) {
                    measures.add(floatMeasure);
                }
                break;
            case 4:
                ArrayList<DoubleMeasureEntity> doubleMeasures = entityDAO.getLastNDoubles(id, num);
                for (DoubleMeasureEntity doubleMeasure : doubleMeasures) {
                    measures.add(doubleMeasure);
                }
                break;
            case 5:
                ArrayList<BooleanMeasureEntity> booleanMeasures = entityDAO.getLastNBooleans(id, num);
                for (BooleanMeasureEntity booleanMeasure : booleanMeasures) {
                    measures.add(booleanMeasure);
                }
                break;
            default:
                System.out.print("Error in getLastNMeasures: incorrect data type id");
        }
        return measures;
    }

    private MetricEntity getMetricById(Long id){
        ApplicationContext context = new FileSystemXmlApplicationContext(
                "C:/Users/Oleg/IdeaProjects/SystemMonitoring/web/resources/Spring-Module.xml");
        MetricDAO metricDAO = (MetricDAO) context.getBean("metricDAO");
        MetricEntity metric = metricDAO.getMetricById(id);
        return metric;
    }

    private Long getDataTypeById(Long id){
       MetricEntity metric = getMetricById(id);
       Long dataTypeId = metric.getDataTypeId();
       return dataTypeId;
    }

    public String getMetricNameById(Long id){
        MetricEntity metric = getMetricById(id);
        String name = metric.getName();
        return name;
    }

    public ArrayList<MeasureEntity> getMeasuresFromPeriod(Long id, Timestamp start, Timestamp end){
        ApplicationContext context = new FileSystemXmlApplicationContext(
                "C:/Users/Oleg/IdeaProjects/SystemMonitoring/web/resources/Spring-Module.xml");
        EntityDAO entityDAO = (EntityDAO) context.getBean("entityDAO");
        Long dataTypeId = getDataTypeById(id);
        ArrayList<MeasureEntity> measures = new ArrayList<MeasureEntity>();
        switch(dataTypeId.intValue()) {
            case 1:
                ArrayList<IntegerMeasureEntity> intMeasures = entityDAO.getIntegerMeasuresFromPeriod(id, start, end);
                for (IntegerMeasureEntity intMeasure : intMeasures) {
                    measures.add(intMeasure);
                }
                break;
            case 2:
                ArrayList<LongMeasureEntity> longMeasures = entityDAO.getLongMeasuresFromPeriod(id, start, end);
                for (LongMeasureEntity longMeasure : longMeasures) {
                    measures.add(longMeasure);
                }
                break;
            case 3:
                ArrayList<FloatMeasureEntity> floatMeasures = entityDAO.getFloatMeasuresFromPeriod(id, start, end);
                for (FloatMeasureEntity floatMeasure : floatMeasures) {
                    measures.add(floatMeasure);
                }
                break;
            case 4:
                ArrayList<DoubleMeasureEntity> doubleMeasures = entityDAO.getDoubleMeasuresFromPeriod(id, start, end);
                for (DoubleMeasureEntity doubleMeasure : doubleMeasures) {
                    measures.add(doubleMeasure);
                }
                break;
            case 5:
                ArrayList<BooleanMeasureEntity> booleanMeasures = entityDAO.getBooleanMeasuresFromPeriod(id, start, end);
                for (BooleanMeasureEntity booleanMeasure : booleanMeasures) {
                    measures.add(booleanMeasure);
                }
                break;
            default:
                System.out.print("Error in getMeasuresFromPeriod: incorrect data type id");
        }
        return measures;
    }

}
