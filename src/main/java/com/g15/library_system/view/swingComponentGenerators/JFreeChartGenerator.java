package com.g15.library_system.view.swingComponentGenerators;

import com.g15.library_system.view.Style;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class JFreeChartGenerator {

  public static JFreeChart createBarChart(
      String chartTitle,
      String categoryAxis_X,
      String valueAxis_Y,
      DefaultCategoryDataset dataset) {

    JFreeChart barChart =
        ChartFactory.createBarChart(chartTitle, categoryAxis_X, valueAxis_Y, dataset);
    CategoryPlot plot = barChart.getCategoryPlot();
    plot.setBackgroundPaint(Style.CHART_BACKGROUND_COLOR);

    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    NumberFormat integerFormat = NumberFormat.getIntegerInstance();
    renderer.setDefaultItemLabelGenerator(
        new StandardCategoryItemLabelGenerator("{2}", integerFormat));
    renderer.setDefaultItemLabelsVisible(true);
    renderer.setDrawBarOutline(false);
    renderer.setBarPainter(new StandardBarPainter());
    renderer.setSeriesPaint(0, Style.GREEN_CONFIRM_BUTTON_COLOR); // set color for the bar

    CategoryAxis domainAxis = plot.getDomainAxis();
    int columnCount = dataset.getColumnCount();

    if (columnCount == 1) {
      domainAxis.setLowerMargin(0.4);
      domainAxis.setUpperMargin(0.4);
      domainAxis.setCategoryMargin(0.0);
    } else if (columnCount == 2) {
      domainAxis.setLowerMargin(0.3);
      domainAxis.setUpperMargin(0.3);
      domainAxis.setCategoryMargin(0.2);
    }

    plot.setNoDataMessage("No data available");
    plot.setNoDataMessageFont(Style.FONT_BOLD_15);
    plot.setNoDataMessagePaint(Style.LOGOUT_RED);

    return barChart;
  }

  public static JFreeChart createLineChart(
      String chartTitle,
      String categoryAxis_X,
      String valueAxis_Y,
      DefaultCategoryDataset dataset) {
    JFreeChart lineChart =
        ChartFactory.createLineChart(chartTitle, categoryAxis_X, valueAxis_Y, dataset);
    CategoryPlot lineChartPlot = lineChart.getCategoryPlot();
    lineChartPlot.setBackgroundPaint(Style.CHART_BACKGROUND_COLOR);
    NumberAxis rangeAxis = (NumberAxis) lineChartPlot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
    //    renderer.setSeriesPaint(0, new Color(20, 169, 20));
    renderer.setSeriesPaint(0, Style.GREEN_CONFIRM_BUTTON_COLOR);
    renderer.setSeriesStroke(0, new BasicStroke(4.0f));
    renderer.setDefaultItemLabelsVisible(true);

    NumberFormat integerFormat = NumberFormat.getIntegerInstance();
    renderer.setDefaultItemLabelGenerator(
        new StandardCategoryItemLabelGenerator("{2}", integerFormat));

    lineChartPlot.setRenderer(renderer);

    lineChartPlot.setNoDataMessage("No data available");
    lineChartPlot.setNoDataMessageFont(Style.FONT_BOLD_15);
    lineChartPlot.setNoDataMessagePaint(Style.LOGOUT_RED);

    return lineChart;
  }

  public static JFreeChart createPieChart(String chartTitle, DefaultPieDataset dataset) {

    JFreeChart pieChart = ChartFactory.createPieChart(chartTitle, dataset, true, true, false);

    PiePlot piePlot = (PiePlot) pieChart.getPlot();
    piePlot.setBackgroundPaint(Style.CHART_BACKGROUND_COLOR);
    piePlot.setLabelGenerator(
        new StandardPieSectionLabelGenerator(
            "{0}: {2}", new DecimalFormat("0"), new DecimalFormat("0.00%")));
    piePlot.setNoDataMessage("No data available");
    piePlot.setNoDataMessageFont(Style.FONT_BOLD_15);
    piePlot.setNoDataMessagePaint(Style.LOGOUT_RED);

    return pieChart;
  }

  public static JFreeChart createStackedBarChart(
      String chartTitle,
      String categoryAxis_X,
      String valueAxis_Y,
      DefaultCategoryDataset dataset) {

    JFreeChart stackedBarChart =
        ChartFactory.createStackedBarChart(chartTitle, categoryAxis_X, valueAxis_Y, dataset);

    CategoryPlot plot = stackedBarChart.getCategoryPlot();
    plot.setBackgroundPaint(Style.CHART_BACKGROUND_COLOR);

    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

    StackedBarRenderer renderer = new StackedBarRenderer();
    NumberFormat integerFormat = NumberFormat.getIntegerInstance();
    renderer.setDefaultItemLabelGenerator(
        new StandardCategoryItemLabelGenerator("{2}", integerFormat));
    renderer.setDefaultItemLabelsVisible(true);
    renderer.setDrawBarOutline(false);
    renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());

    // Gán màu cho từng series (thể loại)
    // renderer.setSeriesPaint(0, Style.GREEN_CONFIRM_BUTTON_COLOR);
    //    renderer.setSeriesPaint(1, Style.CHART_BAR_COLOR_YELLOW);
    //    renderer.setSeriesPaint(2, Style.LOGIN_FRAME_BACKGROUND_COLOR_BLUE);

    plot.setRenderer(renderer);
    CategoryAxis domainAxis = plot.getDomainAxis();
    int columnCount = dataset.getColumnCount();

    if (columnCount == 1) {
      domainAxis.setLowerMargin(0.4);
      domainAxis.setUpperMargin(0.4);
      domainAxis.setCategoryMargin(0.0);
    } else if (columnCount == 2) {
      domainAxis.setLowerMargin(0.3);
      domainAxis.setUpperMargin(0.3);
      domainAxis.setCategoryMargin(0.2);
    }

    plot.setNoDataMessage("No data available");
    plot.setNoDataMessageFont(Style.FONT_BOLD_15);
    plot.setNoDataMessagePaint(Style.LOGOUT_RED);

    return stackedBarChart;
  }
}
