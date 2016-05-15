package ru.sudakovoleg.modelFactories;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import ru.sudakovoleg.dal.measure.MeasureEntity;
import ru.sudakovoleg.sqlAccess.SQLAccessHelper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Oleg on 10.05.2016.
 */
public class LineChartFactory {
    private static final long borderOffset = 3600000;

    public LineChartModel SQLModel(Long[] dataids, String[] labels, int num, String xLabel, String yLabel){
        if(dataids.length != labels.length){
            System.out.print("Incorrect input length, exiting");
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp max = new Timestamp(0);
        LineChartModel model = new LineChartModel();
        SQLAccessHelper accessor = new SQLAccessHelper();
        ArrayList<MeasureEntity> measures;
        for(int i = 0; i<dataids.length; i++){
            LineChartSeries series;
            measures = accessor.getLastNMeasures(dataids[i], num);
            for (MeasureEntity measure : measures){
                if (measure.getTime().after(max)) {max = measure.getTime();}
            }
            series = fillSeries(measures);
            series.setLabel(labels[i]);
            model.addSeries(series);
        }
        model.getAxis(AxisType.Y).setLabel(yLabel);
        DateAxis axis = new DateAxis(xLabel);
        axis.setTickAngle(-50);
        axis.setMax(format.format(new Timestamp(max.getTime()+borderOffset)));
        axis.setTickFormat("%y-%m-%d %H:%M:%S");
        model.getAxes().put(AxisType.X, axis);
        model.setZoom(true);
        model.setLegendPosition("nw");
        model.setAnimate(true);
        return model;
    }

    public LineChartModel SQLModel(Long[] dataids, String[] labels, Timestamp start, Timestamp end,
                                    String yLabel, String xLabel){
        if(dataids.length != labels.length){
            System.out.print("Incorrect input length, exiting");
            return null;

        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LineChartModel model = new LineChartModel();
        SQLAccessHelper accessor = new SQLAccessHelper();
        ArrayList<MeasureEntity> measures;
        for(int i = 0; i<dataids.length; i++){
            LineChartSeries series;
            measures = accessor.getMeasuresFromPeriod(dataids[i], start, end);
            series = fillSeries(measures);
            series.setLabel(labels[i]);
            model.addSeries(series);
        }
        model.getAxis(AxisType.Y).setLabel(yLabel);
        DateAxis axis = new DateAxis(xLabel);
        axis.setTickAngle(-50);
        axis.setMin(format.format(new Timestamp(start.getTime())));
        axis.setMax(format.format(new Timestamp(end.getTime())));
        axis.setTickFormat("%y-%m-%d %H:%M:%S");
        model.getAxes().put(AxisType.X, axis);
        model.setZoom(true);
        model.setLegendPosition("nw");
        model.setAnimate(true);
        return model;
    }

    public LineChartSeries fillSeries(ArrayList<MeasureEntity> measures){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LineChartSeries series = new LineChartSeries();
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
