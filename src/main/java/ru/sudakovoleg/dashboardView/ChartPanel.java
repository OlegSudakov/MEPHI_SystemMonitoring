package ru.sudakovoleg.dashboardView;

import org.primefaces.component.chart.*;
import org.primefaces.component.menu.Menu;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.chart.*;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import ru.sudakovoleg.modelFactories.BarChartFactory;
import ru.sudakovoleg.modelFactories.LineChartFactory;
import ru.sudakovoleg.modelFactories.PieChartFactory;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import java.sql.Timestamp;

/**
 * Created by Oleg on 22.03.2016.
 */
public class ChartPanel extends Panel {

    private Panel panel;
    private Chart chart;
    private Menu menu;
    private MenuModel menuModel;
    private HtmlOutputText text;
    int id;



    public Panel getPanel(){
        return panel;
    }

    public Chart getChart() {return chart;}

    public ChartPanel(){
    }

    public ChartPanel(int index){
        initComponents(index);
        panel.getChildren().add(this.text);
        panel.getChildren().add(this.chart);
        panel.getFacets().put("options", menu);
    }

    public ChartPanel(int index, String header, String text, Long[] dataids, String[] labels){
        initComponents(index, header, text, dataids, labels);
        panel.getChildren().add(this.text);
        panel.getChildren().add(this.chart);
        panel.getFacets().put("options", menu);
    }

    public ChartPanel(int index, String header, String text, Long[] dataids, String[] labels,
                      int num, String xLabel, String yLabel, String chartType){
        initComponents(index, header, text, dataids, labels, num, xLabel, yLabel, chartType);
        panel.getChildren().add(this.text);
        panel.getChildren().add(this.chart);
        panel.getFacets().put("options", menu);
    }

    public ChartPanel(int index, String header, String text, Long[] dataids, String[] labels,
                      Timestamp start, Timestamp end, String xLabel, String yLabel){
        initComponents(index, header, text, dataids, labels, start, end, xLabel, yLabel);
        panel.getChildren().add(this.text);
        panel.getChildren().add(this.chart);
        panel.getFacets().put("options", menu);
    }

    private void initComponents(int index, String header, String text, Long[] dataids, String[] labels){
        id = index;
        facesInit();
        initPanel(index, header);
        initText(index, text);
        initMenu(index);
        initChart(index, dataids, labels);
    }

    private void initComponents(int index){
        id = index;
        facesInit();
        initPanel(index, "Test Panel "+index);
        initText(index, "Dashboard widget " + index);
        initMenu(index);
        initChart(index);
    }

    private void initComponents(int index, String header, String text, Long[] dataids,
                                String[] labels, int num, String xLabel, String yLabel, String chartType){
        facesInit();
        initPanel(index, header);
        initText(index, text);
        initMenu(index);
        initChart(index, dataids, labels, num, xLabel, yLabel, chartType);
    }

    private void initComponents(int index, String header, String text, Long[] dataids,
                                String[] labels, Timestamp start, Timestamp end, String xLabel,
                                String yLabel){
        id = index;
        facesInit();
        initPanel(index, header);
        initText(index, text);
        initMenu(index);
        initChart(index, dataids, labels, start, end, xLabel, yLabel);
    }

    private void initText(int index, String input){
        text = new HtmlOutputText();
        text.setValue(input);
    }

    private void initPanel(int index, String header){
        panel.setId("panel_" + index);
        panel.setHeader(header);
        panel.setClosable(true);
        panel.setToggleable(true);
    }

    private void initChart(int index){
        chart.setId("chart_" + index);
        chart.setType("line");
        LineChartModel model = initLinearModel();
        chart.setModel(model);
    }

    private void initChart(int index, Long[] dataids, String[] labels, int num, String xLabel,
                           String yLabel, String chartType){
        chart.setId("chart_" + index);
        if (chartType == "line"){
            chart.setType("line");
            LineChartFactory factory = new LineChartFactory();
            LineChartModel model = factory.SQLModel(dataids, labels, num, xLabel, yLabel);
            chart.setModel(model);
        }
        else if (chartType == "bar"){
            chart.setType("bar");
            BarChartFactory factory = new BarChartFactory();
            BarChartModel model = factory.SQLModel(dataids, labels, num, xLabel, yLabel);
            chart.setModel(model);
        }
        else {
            System.out.println("Error in initChart with num");
        }
    }

    private void initChart(int index, Long[] dataids, String[] labels, Timestamp start,
            Timestamp end, String xLabel, String yLabel){
        chart.setId("chart_" + index);
        chart.setType("line");
        LineChartFactory factory = new LineChartFactory();
        LineChartModel model = factory.SQLModel(dataids, labels, start, end, xLabel, yLabel);
        chart.setModel(model);
    }

    private void initChart(int index, Long[] dataids, String[] labels){
        chart.setId("chart_" + index);
        chart.setType("pie");
        PieChartFactory factory = new PieChartFactory();
        PieChartModel model = factory.SQLModel(dataids, labels);
        chart.setModel(model);
    }

    private void initMenu(int index){
        menuModel = new DefaultMenuModel();
        DefaultSubMenu submenu = new DefaultSubMenu("Configuration");
        DefaultMenuItem zoomItem = new DefaultMenuItem("Zoom");
        zoomItem.setIcon("ui-icon-extlink");
        zoomItem.setId("zoom_"+index);
        zoomItem.setCommand("#{dashboardModelView.zoomChart("+index+")}"); //Set command later
        zoomItem.setUpdate("zoomPanel");
        DefaultMenuItem settingsItem = new DefaultMenuItem("Settings");
        settingsItem.setIcon("ui-icon-gear");
        settingsItem.setId("settings_"+index);
        settingsItem.setCommand(""); //Set command later
        submenu.addElement(zoomItem);
        submenu.addElement(settingsItem);
        menuModel.addElement(submenu);
        menu.setModel(menuModel);
    }
    //Test linear chart
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(id+1, id+2);
        series1.set(id+2, id+1);
        series1.set(id+3, id+3);
        series1.set(id+4, id+6);
        series1.set(id+5, id+8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(id+1, id+6);
        series2.set(id+2, id+3);
        series2.set(id+3, id+2);
        series2.set(id+4, id+7);
        series2.set(id+5, id+9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    public void updateSqlModel(Long[] dataids, String[] labels, int num, String xLabel, String yLabel){
        LineChartFactory factory = new LineChartFactory();
        chart.setModel(factory.SQLModel(dataids, labels, num, yLabel, xLabel));
    }

    private void facesInit(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
        panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel",
                "org.primefaces.component.PanelRenderer");
        chart = (Chart) application.createComponent(fc, "org.primefaces.component.Chart",
                "org.primefaces.component.ChartRenderer");
        menu = (Menu) application.createComponent(fc, "org.primefaces.component.Menu",
                "org.primefaces.component.MenuRenderer");
    }

}
