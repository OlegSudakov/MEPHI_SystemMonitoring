package ru.sudakovoleg.dashboardView;

import org.primefaces.component.chart.Chart;
import org.primefaces.model.chart.ChartModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by Oleg on 29.03.2016.
 */

@ManagedBean
@ViewScoped
public class ZoomHandler {

    private Chart zoomedChart;
    private String zoomedChartType;
    private ChartModel zoomedChartModel;
    private String chartName;
    private String isRendered;

    public void zoomChart(int id){
        //Can't handle shit now
    }

    public Chart getZoomedChart() {
        return zoomedChart;
    }

    public void setZoomedChart(Chart zoomedChart) {
        this.zoomedChart = zoomedChart;
    }

    public String getZoomedChartType() {
        return zoomedChartType;
    }

    public void setZoomedChartType(String zoomedChartType) {
        this.zoomedChartType = zoomedChartType;
    }

    public ChartModel getZoomedChartModel() {
        return zoomedChartModel;
    }

    public void setZoomedChartModel(ChartModel zoomedChartModel) {
        this.zoomedChartModel = zoomedChartModel;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public String getIsRendered() {
        return isRendered;
    }

    public void setRendered(String rendered) {
        isRendered = rendered;
    }
}
