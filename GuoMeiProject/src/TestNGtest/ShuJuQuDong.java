package TestNGtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ShuJuQuDong {
    WebDriver driver;
    String baseUrl="https://www.baidu.com";
    String baseDir=System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver",baseDir+"/driver/chromedriver");
        driver=new ChromeDriver();

    }
    @AfterClass
    public void afterClass() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }
    @DataProvider(name="searchWords")
    public Object[][] dataPro(){
        return new Object[][]{{"私人订制","葛优"},{"千与千寻","动画片"}};
    }

    @Test(dataProvider = "searchWords")
    public void test(String testData,String expectData) throws InterruptedException {
        driver.get(baseUrl);
        driver.findElement(By.id("kw")).sendKeys(testData);
        Thread.sleep(5000);
        Assert.assertTrue(driver.getPageSource().contains(expectData));
    }



}
