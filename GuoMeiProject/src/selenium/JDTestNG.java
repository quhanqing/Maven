package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class JDTestNG {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        // String baseDir = System.getProperty("user.dir");
        //String driverPath = baseDir + "/driver/chromedriver.exe";
        //System.setProperty("webdriver.chrome.driver",driverPath);
        //driver = new ChromeDriver();
        // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver = new FirefoxDriver();
        //System.setProperty("webdriver.firefox.bin","D:\\FireFox");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


    @Test(enabled = false)
    public void addCartTest() {
        driver.get("https://search.jd.com/Search?keyword=Mac%20pro&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&bs=1&wq=Mac%20pro&psort=5&ev=exbrand_Apple%5E&click=0");

        String handle = driver.getWindowHandle();
        System.out.println("第一个tab页面的handle：" + handle);

        driver.findElement(By.xpath("//ul[@class='gl-warp clearfix']/li[2]//img")).click();
        // driver.findElement(By.cssSelector("ul.gl-warp.clearfix >li:nth-child(2)")).click();

        Set<String> handles = driver.getWindowHandles();

        for (String s : handles) {
            System.out.println(s);
            if (!s.equals(handle)) {
                driver.switchTo().window(s);
                System.out.println("切换第二个窗口成功");
            }
        }

        driver.findElement(By.id("InitCartUrl")).click();
        //div[@id='choose-btns']//a[contains(text(),'加入购物车')]
        String text = driver.findElement(By.xpath("//div[@id='result']//h3")).getText();

        Assert.assertEquals(text, "商品已成功加入购物车！");
    }
        @Test
        public void fireFoxBrs(){

        }



    }

