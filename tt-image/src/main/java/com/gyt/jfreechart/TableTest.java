package com.gyt.jfreechart;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import java.io.File;
import java.io.IOException;

public class TableTest {
    public static void main(String[] args) {
        // 行名称
        String[] domainNames = new String[]{"第一行", "第二行", "第三行"};

        // 列名称
        String[] rangeNames = new String[]{"第一列","第二列","第三列"};

        // 表格数据
        double[][] data = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        // 创建数据集
        DefaultTableXYDataset dataset = new DefaultTableXYDataset();
        for (int i = 0; i < domainNames.length; i++) {
            XYSeries series = new XYSeries(domainNames[i]);
            for (int j = 0; j < rangeNames.length; j++) {
                boolean exists = false;
                for (int k = 0; k < series.getItemCount(); k++) {
                    XYDataItem item = series.getDataItem(k);
                    if (item.getXValue() == j) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    series.add(j, data[i][j]);
                }
            }
            dataset.addSeries(series);
        }

        XYPlot plot = new XYPlot(dataset,
                new NumberAxis("Domain"),
                new NumberAxis("Range"),
                null);

        JFreeChart chart = new JFreeChart("Excel Style Table Demo",
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);

        try {
            ChartUtilities.saveChartAsPNG(new File("table.png"), chart, 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
