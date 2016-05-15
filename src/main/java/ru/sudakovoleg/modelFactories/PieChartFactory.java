package ru.sudakovoleg.modelFactories;

import org.primefaces.model.chart.PieChartModel;
import ru.sudakovoleg.dal.measure.MeasureEntity;
import ru.sudakovoleg.sqlAccess.SQLAccessHelper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Oleg on 10.05.2016.
 */
public class PieChartFactory {

    public PieChartModel SQLModel(Long[] dataids, String[] labels){
        if(dataids.length != labels.length){
            System.out.print("Incorrect input length, exiting");
            return null;
        }
        PieChartModel model = new PieChartModel();
        model.setShowDataLabels(true);
        model.setDatatipFormat("%s - %g");
        SQLAccessHelper accessor = new SQLAccessHelper();
        ArrayList<MeasureEntity> measures = new ArrayList<MeasureEntity>();
        for(int i = 0; i<dataids.length; i++) {
            measures = accessor.getLastNMeasures(dataids[i], 1);
            MeasureEntity measure = measures.get(0);
            model.set(labels[i], (Number) measure.getValue());
            model.setTitle("Time: "+measure.getTime());
        }
        if (dataids.length == 1){
            model.set("Other", 1-((Number)measures.get(0).getValue()).floatValue());
        }
        model.setLegendPosition("nw");
        return model;
    }

}
