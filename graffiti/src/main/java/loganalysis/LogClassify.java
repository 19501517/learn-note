package loganalysis;

import com.alibaba.fastjson.JSON;
import constants.LogConstants;
import loganalysis.model.GoldLogParamsModel;
import loganalysis.model.LogModel;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author liuyefeng
 * @Date 2018/12/29 14:18
 */
public class LogClassify {

    private static final ExecutorService executors =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        File baseDir = new File(LogConstants.BASE_PATH);
        for (File serverLogDir : baseDir.listFiles()) {
            if (serverLogDir.isDirectory()) {
                doScanDir(serverLogDir);
            }
        }
        long end = System.currentTimeMillis();

        System.out.println("classify total use time " + ((end - begin) / 1000));
    }

    private static void doScanDir(File serverLogDir) throws Exception {
        String serverName = serverLogDir.getName();
        for (File logFile : serverLogDir.listFiles()) {
            if (logFile.isDirectory()) {
                continue;
            }
            doClassifyLogFile(serverName, logFile);
        }
    }

    private static void doClassifyLogFile(String serverName, File logFile) throws Exception {
        System.out.println("begin to classify " + logFile.getName());
        String logFileName = logFile.getName();
        String time = logFileName.substring(logFileName.lastIndexOf(".") + 1, logFileName.length());

        List<String> loginLogs = new ArrayList<>();
        List<String> rechargeLogs = new ArrayList<>();
        List<String> costLogs = new ArrayList<>();

        int totalLine = 0;
        try (Scanner scanner = new Scanner(logFile)) {
            String line = null;
            while (scanner.hasNextLine()) {
                totalLine++;
                line = scanner.nextLine();

                LogModel log = JSON.parseObject(line, LogModel.class);
                if (log.getType().equals("PlayerLogin")) {
                    loginLogs.add(line);
                }

                if (log.getType().equals("Gold")) {
                    GoldLogParamsModel goldLogModel = JSON.parseObject(log.getProp(), GoldLogParamsModel.class);
                    if (Long.valueOf(goldLogModel.getDiff()) < 0) {
                        costLogs.add(line);
                    } else {
                        if (goldLogModel.getType().equals("130001")) {
                            rechargeLogs.add(line);
                        }
                    }
                }

            }
        } catch (Exception e) {
            throw e;
        }

        System.out.println("totalLine:" + totalLine);
        System.out.println("loginLogs:" + loginLogs.size());
        System.out.println("rechargeLogs:" + rechargeLogs.size());
        System.out.println("costLogs:" + costLogs.size());

        writeFile(LogConstants.RETAIN_PATH + "/" + serverName + "/" + time, loginLogs);
        writeFile(LogConstants.GOLD_PATH + "/" + serverName + "/recharge/" + time, rechargeLogs);
        writeFile(LogConstants.GOLD_PATH + "/" + serverName + "/cost/" + time, costLogs);

        System.out.println();
    }

    private static void writeFile(String fileName, List<String> contents) throws Exception {
        System.out.println("start to write file " + fileName);

        File file = new File(fileName);
        if (file.exists()) {
            FileUtils.forceDelete(file);
        }
        if (!file.getParentFile().exists()) {
            FileUtils.forceMkdir(file.getParentFile());
        }
        FileUtils.writeLines(file, contents);
    }

}
