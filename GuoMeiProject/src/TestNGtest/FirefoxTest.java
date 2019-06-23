package TestNGtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirefoxTest {
    WebDriver webDriver;
    @BeforeClass
    public void beforeClass(){

        String baseDir=System.getProperty("user.dir");
        System.setProperty("webdriver.gecko.driver",baseDir+"/driver/geckodriver");
        webDriver=new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }


    @Test
    public void test(){
     String title=webDriver.getTitle();
     System.out.println(title);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
