package utils;

import java.awt.Font;
import java.io.FileOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {

    private String fontName;
    private int fontType;
    private int fontSize;

    private String TitleFontName;
    private int TitleFontType;
    private int TitleFontSize;

    public PieChart(String TitleFontName, int TitleFontType, int TitleFontSize, String fontName, int fontType, int fontSize) {
        this.fontName = fontName;
        this.fontType = fontType;
        this.fontSize = fontSize;

        this.TitleFontName = TitleFontName;
        this.TitleFontType = TitleFontType;
        this.TitleFontSize = TitleFontSize;
    }

    public PieChart() {
        this.fontName = "微软雅黑";
        this.fontType = Font.BOLD;
        this.fontSize = 11;

        this.TitleFontName = "微软雅黑";
        this.TitleFontType = Font.BOLD;
        this.TitleFontSize = 30;
    }

    public boolean createPieChart(String fileUrl, String titleName, String[] properties, int[] values, int width, int hight) {
        DefaultPieDataset dpd = getPieChartSet(properties, values);

        JFreeChart chart = ChartFactory.createPieChart(titleName, dpd, true, false, false);

        Font titleFont = new Font(TitleFontName, TitleFontType, TitleFontSize);
        Font chartFont = new Font(fontName, fontType, fontSize);

        TextTitle title = new TextTitle(titleName, titleFont);

        chart.setTitle(title);
        chart.getLegend().setItemFont(chartFont);

        PiePlot plot = (PiePlot) chart.getPlot();

        plot.setLabelFont(chartFont);

        FileOutputStream category_jpeg = null;

        try {
            category_jpeg = new FileOutputStream(fileUrl);
            ChartUtilities.writeChartAsJPEG(category_jpeg, 1, chart, width, hight);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                category_jpeg.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return true;

    }

    private DefaultPieDataset getPieChartSet(String[] properties, int[] values) {
        DefaultPieDataset set = new DefaultPieDataset();

        int sum = properties.length;
        for(int i = 0; i < sum; i++) {
            set.setValue(properties[i], values[i]);
        }
        return set;
    }

    public static void main(String[] args) {
        String[] properties = new  String[]{"水果1", "水果2", "水果3"};
        int[] values = new  int[]{1, 2, 3};

        new PieChart().createPieChart("E:\\chart.jpeg", "水果", properties, values, 1000, 700);
    }
}