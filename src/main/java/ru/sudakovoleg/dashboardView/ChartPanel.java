package ru.sudakovoleg.dashboardView;

import org.primefaces.component.chart.*;
import org.primefaces.component.menu.Menu;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.chart.*;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.webapp.FacetTag;

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
        panel.getChildren().add(text);
        panel.getChildren().add(chart);
        panel.getFacets().put("options", menu);
    }

    private void initComponents(int index){
        id = index;
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
        panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel",
                "org.primefaces.component.PanelRenderer");
        chart = (Chart) application.createComponent(fc, "org.primefaces.component.Chart",
                "org.primefaces.component.ChartRenderer");
        menu = (Menu) application.createComponent(fc, "org.primefaces.component.Menu",
                "org.primefaces.component.MenuRenderer");
        initPanel(index);
        initText(index);
        initMenu(index);
        initChart(index);
    }

    private void initText(int index){
        text = new HtmlOutputText();
        text.setValue("Dashboard widget " + index);
    }

    private void initPanel(int index){
        panel.setId("panel_" + index);
        panel.setHeader("Test Panel " + index);
        panel.setClosable(true);
        panel.setToggleable(true);
    }

    private void initChart(int index){
        chart.setId("chart_" + index);
        chart.setType("line");
        LineChartModel model = initLinearModel();
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
}
