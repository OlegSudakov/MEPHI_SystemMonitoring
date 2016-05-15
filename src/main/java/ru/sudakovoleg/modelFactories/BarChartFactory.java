package ru.sudakovoleg.modelFactories;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import ru.sudakovoleg.dal.measure.MeasureEntity;
import ru.sudakovoleg.sqlAccess.SQLAccessHelper;
import org.primefaces.model.chart.DateAxis;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Oleg on 10.05.2016.
 */
public class BarChartFactory {
    private static final long borderOffset = 1000000;

    public BarChartModel SQLModel(Long[] dataids, String[] labels, int num, String xLabel, String yLabel){
        if(dataids.length != labels.length){
            System.out.print("Incorrect input length, exiting");
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BarChartModel model = new BarChartModel();
        SQLAccessHelper accessor = new SQLAccessHelper();
        ArrayList<MeasureEntity> measures;
        DateAxis axis = new DateAxis(xLabel);
        for(int i = 0; i<dataids.length; i++){
            ChartSeries series;
            measures = accessor.getLastNMeasures(dataids[i], num);
            if (i == 0){
                axis.setMin(format.format(new Timestamp(measures.get(num-1).getTime().getTime()-borderOffset)));
                axis.setMax(format.format(new Timestamp(measures.get(0).getTime().getTime()+borderOffset)));
            }
            series = fillSeries(measures);
            series.setLabel(labels[i]);
            model.addSeries(series);
        }
        System.out.println(model.getBarWidth());
        model.setBarWidth(10);
        model.getAxis(AxisType.Y).setLabel(yLabel);
        axis.setTickAngle(-50);
        axis.setTickFormat("%y-%m-%d %H:%M:%S");
        model.getAxes().put(AxisType.X, axis);
        model.setZoom(true);
        model.setLegendPosition("nw");
        model.setAnimate(true);
        return model;
    }

    public ChartSeries fillSeries(ArrayList<MeasureEntity> measures){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ChartSeries series = new ChartSeries();
        for (MeasureEntity measure : measures){
            if (measure.getValue().getClass().getName() == "java.lang.Boolean"){
                series.set(format.format(measure.getTime()), (Boolean) measure.getValue() ? 1 : 0);
            }
            else{
                series.set(format.format(measure.getTime()), (Number) measure.getValue());
            }

        }
        return series;
    }

}
