package goldloganalysis;

import com.alibaba.fastjson.JSON;
import constants.LogConstants;
import org.apache.commons.io.FileUtils;
import utils.CategoryChart;

import java.io.File;
import java.util.*;

public class GoldLogLuncher {

    private static final String RESULT_PATH = LogConstants.GOLD_PATH + "/result";

    public static void main(String[] args) throws Exception {
        File goldLog = new File(LogConstants.GOLD_PATH);
        for (File file : goldLog.listFiles()) {
            doAnalysis(file);
        }
    }

    private static void doAnalysis(File file) throws Exception {
        String server = file.getName();
        if (!isServerName(server)) {
            return;
        }

        String costLogPath = file.getAbsolutePath() + "/cost";
        String rechargePath= file.getAbsolutePath() + "/recharge";

        doAnalysisTotalCost(server, costLogPath);
        doAnalysisTotalRecharge(server, rechargePath);
    }

    private static void doAnalysisTotalRecharge(String server, String rechargePath) throws Exception {
        Map<String, Integer> dayRecharges = new TreeMap<>();
        File curDir = new File(rechargePath);
        for (File logFile : curDir.listFiles()) {
            String date = logFile.getName();

            List<GoldLogModel> goldLogModels = loadLogModel(logFile);
            int totalRecharge = 0;
            for (GoldLogModel model : goldLogModels) {
                totalRecharge += Long.valueOf(model.getProp().getDiff());
            }

            dayRecharges.put(date, totalRecharge);
        }

        File outputDir = new File(RESULT_PATH + "/" + server);
        if (!outputDir.exists()) {
            FileUtils.forceMkdir(outputDir);
        }
        String[] properties = dayRecharges.keySet().toArray(new String[0]);
        Integer[] values = dayRecharges.values().toArray(new Integer[0]);
        CategoryChart chart = new CategoryChart();
        chart.createCategoryChart(outputDir.getAbsolutePath() + "/totalRecharge.jpeg", "总充值元宝", "date", "amount",
                properties, values, 1000, 1000);
    }

    private static void doAnalysisTotalCost(String server, String costLogPath) throws Exception {
        Map<String, Integer> dayCost = new TreeMap<>();
        File curDir = new File(costLogPath);
        for (File logFile : curDir.listFiles()) {
            String date = logFile.getName();

            List<GoldLogModel> goldLogModels = loadLogModel(logFile);
            int totalRecharge = 0;
            for (GoldLogModel model : goldLogModels) {
                totalRecharge += -Long.valueOf(model.getProp().getDiff());
            }

            dayCost.put(date, totalRecharge);
        }

        File outputDir = new File(RESULT_PATH + "/" + server);
        if (!outputDir.exists()) {
            FileUtils.forceMkdir(outputDir);
        }
        String[] properties = dayCost.keySet().toArray(new String[0]);
        Integer[] values = dayCost.values().toArray(new Integer[0]);
        CategoryChart chart = new CategoryChart();
        chart.createCategoryChart(outputDir.getAbsolutePath() + "/totalCost.jpeg", "总消耗元宝", "date", "amount",
                properties, values, 1000, 1000);
    }

    private static List<GoldLogModel> loadLogModel(File logFile) throws Exception {
        List<GoldLogModel> result = new ArrayList<>();
        try (Scanner s = new Scanner(logFile)) {
            while (s.hasNextLine()) {
                result.add(JSON.parseObject(s.nextLine(), GoldLogModel.class));
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public static boolean isServerName(String serverName) {
        try {
            Integer.valueOf(serverName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
