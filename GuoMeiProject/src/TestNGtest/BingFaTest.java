package TestNGtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BingFaTest {
    WebDriver driver;
    String baseUrl="https://www.baidu.com";
    String baseDir=System.getProperty("user.dir");
    @Parameters("browser")
    @BeforeClass
    public  void beforeClass(String liulanqi){
     /*   if(browser.equals("Firefox")){
            System.setProperty("webdriver.gecko.driver",baseDir+"/driver/geckodriver");
            driver=new FirefoxDriver();
        }
        else{
            System.setProperty("webdriver.chrome.driver",baseDir+"/driver/geckodriver");
            driver=new ChromeDriver();
        }
      */

     switch (liulanqi){
         case "Firefox":
             System.setProperty("webdriver.gecko.driver",baseDir+"/driver/geckodriver");
             driver=new FirefoxDriver();
             break;
         case "Chrome":
             System.setProperty("webdriver.chrome.driver",baseDir+"/driver/chromedriver");
             driver=new ChromeDriver();
             break;
             default:
                 System.out.println("传入浏览器名称不对");
     }
    }
    @Test
    public void test(){
        driver.get(baseUrl);
        driver.findElement(By.id("kw")).sendKeys("私人定制");
    }
    @AfterClass
    public void afterClass(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
