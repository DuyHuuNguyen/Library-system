package com.g15.library_system.view.swingComponentGenerators;

import com.g15.library_system.view.Style;
import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
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
    BarRenderer renderer = (BarRenderer) plot.getRenderer();

    renderer.setBarPainter(new StandardBarPainter());
    renderer.setSeriesPaint(0, Style.GREEN_CONFIRM_BUTTON_COLOR); // set color for the bar
    //        renderer.setSeriesPaint(1, Style.CHART_BAR_COLOR_YELLOW);
    //        renderer.setSeriesPaint(2, Style.LOGIN_FRAME_BACKGROUND_COLOR_BLUE);

    renderer.setDrawBarOutline(false);
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
    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
    //    renderer.setSeriesPaint(0, new Color(20, 169, 20));
    renderer.setSeriesPaint(0, Style.CONFIRM_BUTTON_COLOR_GREEN);
    renderer.setSeriesStroke(0, new BasicStroke(4.0f));
    renderer.setDefaultItemLabelsVisible(true);
    renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    lineChartPlot.setRenderer(renderer);
    return lineChart;
  }

  public static JFreeChart createPieChart(String chartTitle, DefaultPieDataset dataset) {

    JFreeChart pieChart = ChartFactory.createPieChart(chartTitle, dataset, true, true, false);

    PiePlot ordersPlot = (PiePlot) pieChart.getPlot();
    ordersPlot.setBackgroundPaint(Style.CHART_BACKGROUND_COLOR);
    ordersPlot.setLabelGenerator(
        new StandardPieSectionLabelGenerator(
            "{0}: {2}", new DecimalFormat("0"), new DecimalFormat("0.00%")));

    return pieChart;
  }
}
