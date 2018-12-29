package ipinfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author liuyefeng
 * @Date 2018/12/18 15:48
 */
public class MainTest {

    //    private static final String QUERY_URL = "http://ip-api.com/json/%s?lang=zh-CN";
    private static final String QUERY_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=%s";
    private static final String EXCEL_PATH = "D:/player.xlsx";
    private static final String IP_JSON_PATH = "D:/ip.txt";

    private static final CloseableHttpClient CLIENT = HttpClients.createDefault();
    private static String COUNTRY = "country";
    private static String CITY = "city";
    private static String REGION = "region";
    private static String ISP = "isp";

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File(IP_JSON_PATH));
        String ipJson = null;
        if (s.hasNextLine()) {
            ipJson = s.nextLine();
        }
        s.close();
        Map<String, IpInfo> ipInfos = new HashMap<>();
        if (ipJson != null) {
            ipInfos = JSON.parseObject(ipJson, new TypeReference<HashMap<String, IpInfo>>() {
            });
        }

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(EXCEL_PATH));
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell ipCell = row.getCell(1);
            String ip = ipCell.getStringCellValue();
            IpInfo ipInfo;
            if (ipInfos.containsKey(ip)) {
                ipInfo = ipInfos.get(ip);
            } else {
                ipInfo = getIpInfoVO(ip);
                while (ipInfo == null) {
                    Thread.sleep(10000);
                    ipInfo = getIpInfoVO(ip);
                }
                ipInfos.put(ip, ipInfo);
            }

            Cell countryCell = row.createCell(3);
            Cell regionCell = row.createCell(4);
            Cell cityCell = row.createCell(5);
            Cell ispCell = row.createCell(6);

            countryCell.setCellValue(ipInfo.getCountry());
            regionCell.setCellValue(ipInfo.getRegion());
            cityCell.setCellValue(ipInfo.getCity());
            ispCell.setCellValue(ipInfo.getIsp());
        }

        FileOutputStream xlsxFos = new FileOutputStream(EXCEL_PATH);
        workbook.write(xlsxFos);
        xlsxFos.close();
        workbook.close();

        FileOutputStream ipJsonFos = new FileOutputStream(IP_JSON_PATH);
        ipJsonFos.write(JSON.toJSONString(ipInfos).getBytes());
        ipJsonFos.flush();
        ipJsonFos.close();
    }

    private static IpInfo getIpInfoVO(String ip) throws Exception {
        try {
//            HttpGet get = new HttpGet(String.format(QUERY_URL, ip));
//            CloseableHttpResponse response = CLIENT.execute(get);
//            String json = EntityUtils.toString(response.getEntity());
//            response.close();|

            URL url = new URL(String.format(QUERY_URL, ip));
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.connect();
            InputStream inputStream = urlCon.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String json = bufferedReader.readLine();

            IpInfo info = new IpInfo();
            JSONObject jsonObject = JSON.parseObject(json).getJSONObject("data");
            info.setRegion(jsonObject.getString(REGION));
            info.setCountry(jsonObject.getString(COUNTRY));
            info.setCity(jsonObject.getString(CITY));
            info.setIsp(jsonObject.getString(ISP));
            System.out.println(info);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
