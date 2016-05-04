package ru.sudakovoleg.dashboardView;

/**
 * Created by Oleg on 20.03.2016.
 */

import java.io.Serializable;
import java.util.HashMap;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.chart.Chart;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.poll.Poll;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean
@ViewScoped
public class DashboardModelView extends ChartPanel implements Serializable {

    private int index = 0;
    private Dashboard dashboard;

    private DashboardModel model;
    public static final int DEFAULT_COLUMN_COUNT = 3;
    private int columnCount = DEFAULT_COLUMN_COUNT;
    private HashMap<Integer, ChartPanel> chartPanelMap = new HashMap<Integer, ChartPanel>();

    private String zoomedChartName = "None";
    private boolean usingZoom = false;
    private Chart chartToZoom;
    private String chartToZoomType = "None";

    public void zoomChart(int id){ //TODO
        ChartPanel panel = chartPanelMap.get(id);
        chartToZoom = panel.getChart();
        chartToZoomType = chartToZoom.getType();
        zoomedChartName = panel.getHeader();
        usingZoom = true;
        RequestContext.getCurrentInstance().update(":zoomPanel");
    }

    public DashboardModelView() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();

        dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard",
                "org.primefaces.component.DashboardRenderer");
        dashboard.setId("dashboard");

        model = new DefaultDashboardModel();
        for (int i = 0, n = getColumnCount(); i < n; i++) {
            DashboardColumn column = new DefaultDashboardColumn();
            model.addColumn(column);
        }
        dashboard.setModel(model);

        int items = 3;
        for (int i = 0, n = items; i < n; i++) {
            Panel panel = createPanel(index++);
            getDashboard().getChildren().add(panel);
            DashboardColumn column = model.getColumn(i % getColumnCount());
            column.addWidget(panel.getId());
        }
    }

    public Dashboard getDashboard(){
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard){
        this.dashboard = dashboard;
    }

    public int getColumnCount(){
        return columnCount;
    }

    public void setColumnCount(int columnCount){
        this.columnCount = columnCount;
    }

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());

        addMessage(message);
    }

    Panel createPanel(int index){
        ChartPanel chartPanel = new ChartPanel(index);
        chartPanelMap.put(index, chartPanel);
        return chartPanel.getPanel();
    }

    public void addPanel(){
        model = dashboard.getModel();
        Panel panel = createPanel(index++);
        getDashboard().getChildren().add(panel);
        int minNum = Integer.MAX_VALUE;
        for (DashboardColumn column : model.getColumns()){
            if (column.getWidgetCount() <= minNum){
                minNum = column.getWidgetCount();
            }
        }
        for (DashboardColumn column : model.getColumns()){
            if (column.getWidgetCount()==minNum){
                column.addWidget(panel.getId());
                break;
            }
        }
        dashboard.setModel(model);
    }

    public void clearDashboard(){
        dashboard.getChildren().clear();
        chartPanelMap.clear();
        index = 0;
        model = new DefaultDashboardModel();
        for (int i = 0, n=getColumnCount(); i<n; i++){
            model.addColumn(new DefaultDashboardColumn());
        }
        dashboard.setModel(model);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public HashMap<Integer, ChartPanel> getChartPanelMap() {
        return chartPanelMap;
    }

    public void setChartPanelMap(HashMap<Integer, ChartPanel> chartPanelMap) {
        this.chartPanelMap = chartPanelMap;
    }

    public String getZoomedChartName() {
        return zoomedChartName;
    }

    public boolean isUsingZoom() {
        return usingZoom;
    }

    public Chart getChartToZoom() {
        return chartToZoom;
    }

    public String getChartToZoomType() {
        return chartToZoomType;
    }

    public void setZoomedChartName(String zoomedChartName) {
        this.zoomedChartName = zoomedChartName;
    }

    public void setUsingZoom(boolean usingZoom) {
        this.usingZoom = usingZoom;
    }

    public void setChartToZoom(Chart chartToZoom) {
        this.chartToZoom = chartToZoom;
    }

    public void setChartToZoomType(String chartToZoomType) {
        this.chartToZoomType = chartToZoomType;
    }
}

