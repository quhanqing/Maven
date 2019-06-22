package selenium;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScreenShotFileUtils {
    public static void main(String[] args) {

        String baseDir = System.getProperty("user.dir");
        String driverPath = baseDir + "/driver/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);


        for (int i = 0; i < 3; i++) {
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.baidu.com");
            File srcFile = ((ChromeDriver) driver).getScreenshotAs(OutputType.FILE);
            String date=getCurrentDate();
            String[] date0=date.split(" ");
            String date1=date0[0];
            String date2=date0[1];
            try {
                org.apache.commons.io.FileUtils.copyFile(srcFile, new File(baseDir + "/ScreenShot/" +date1+"/"+date2+".png"));

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
    public static String getCurrentDate(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        Date d=calendar.getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=simpleDateFormat.format(d);
        return date;
    }

}
