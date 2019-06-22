package selenium;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BaiduCollectHttpResponse {
    /*
    百度首页-console- window.performance.getEntries()-返回每个请求的时间等参数，
    （收集性能指标）获取所有请求，提取每一个请求中的：
    name  duration(响应时间)  encodedBodySize(大小)  三个字段写入到excel中
     */
    public static void main(String[] arge) throws IOException {


        String baseDir = System.getProperty("user.dir");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("百度首页");
        FileOutputStream fileOutputStream = new FileOutputStream(baseDir + "/TestCase/badiduPerformance03.xlsx");



        String driverPath = baseDir + "/driver/chromedriver";
        System.setProperty("webdriver.chrome.driver",driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.baidu.com/");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        Object res1 = js.executeScript("return window.performance.getEntries()");
        System.out.println(res1.toString());
        int i = 0;
        String res2 = res1.toString();
        String [] res3 = res2.split("}, \\{"); //以}， {分割，转义一下{，返回数组
        for(String s: res3){
            String[] strings = s.split(", ");
            Row row = sheet.createRow(i);
            for(String s2 : strings){

                if (s2.contains("name")){
                    Cell cell = row.createCell(0);
                    cell.setCellValue(s2);
                }
                if(s2.contains("duration")){
                    Cell cell = row.createCell(1);
                    cell.setCellValue(s2);
                }
                //i++;
                //System.out.println(s2);

            }
            i++;
        }

        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();







    }
}
