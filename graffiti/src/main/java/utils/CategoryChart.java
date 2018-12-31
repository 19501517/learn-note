package utils;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

public class CategoryChart {

    private String fontName;
    private int fontType;
    private int fontSize;

    private String TitleFontName;
    private int TitleFontType;
    private int TitleFontSize;

    public CategoryChart(String TitleFontName, int TitleFontType, int TitleFontSize, String fontName, int fontType, int fontSize) {
        this.fontName = fontName;
        this.fontType = fontType;
        this.fontSize = fontSize;

        this.TitleFontName = TitleFontName;
        this.TitleFontType = TitleFontType;
        this.TitleFontSize = TitleFontSize;
    }

    public CategoryChart() {
        this.fontName = "微软雅黑";
        this.fontType = Font.BOLD;
        this.fontSize = 11;

        this.TitleFontName = "微软雅黑";
        this.TitleFontType = Font.BOLD;
        this.TitleFontSize = 30;
    }

    public boolean createCategoryChart(String fileUrl, String titleName, String xName, String yName,
                                       String[] properties, Integer[] values, int width, int hight) throws FileNotFoundException, IOException{

        DefaultCategoryDataset set = getBarChartSet(properties, values);

        JFreeChart categoryChart = ChartFactory.createBarChart3D(
                titleName,
                xName,
                yName,
                set,
                PlotOrientation.VERTICAL,
                false,
                false,
                true);

        Font titleFont = new Font(TitleFontName, TitleFontType, TitleFontSize);
        Font chartFont = new Font(fontName, fontType, fontSize);

        TextTitle title = new TextTitle(titleName, titleFont);
        categoryChart.setTitle(title);

        CategoryPlot plot = categoryChart.getCategoryPlot();
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
        CategoryAxis domainAxis = plot.getDomainAxis();

        domainAxis.setTickLabelFont(chartFont);
        domainAxis.setLabelFont(chartFont);

        numberaxis.setTickLabelFont(chartFont);
        numberaxis.setLabelFont(chartFont);

        try (FileOutputStream category_jpeg = new FileOutputStream(fileUrl)) {
            ChartUtilities.writeChartAsJPEG(category_jpeg, 1, categoryChart, width, hight);
            return true;
        }
    }

    private DefaultCategoryDataset getBarChartSet(String[] properties, Integer[] values) {
        DefaultCategoryDataset set = new DefaultCategoryDataset();

        int sum = properties.length;
        for(int i = 0; i < sum; i++) {
            set.addValue(values[i], properties[i], properties[i]);
        }
        return set;
    }
}