package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebJD {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://passport.jd.com/new/login.aspx?ReturnUrl=https%3A%2F%2Fwww.jd.com%2F");//打开京东登录页面

        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/a"));//切换账号登录
        login.click();
        WebElement userName = webDriver.findElement(By.id("loginname"));
        userName.sendKeys("15101543275");
        WebElement passWord = webDriver.findElement(By.id("nloginpwd"));
        passWord.sendKeys("cuikai9421");
        WebElement loginSubmit = webDriver.findElement(By.id("loginsubmit"));
        loginSubmit.click();
        webDriver.get("https://www.jd.com/");
        WebElement search = webDriver.findElement(By.id("key"));//输入搜索关键字
        search.sendKeys("Mac pro");
        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id=\"search\"]/div/div[2]/button"));//点击搜索按钮
        searchButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"J_filter\"]/div[1]/div[1]/a[4]")));
        WebElement xinpinOrder = webDriver.findElement(By.xpath("//*[@id=\"J_filter\"]/div[1]/div[1]/a[4]"));//按新品排序
        xinpinOrder.click();

        JavascriptExecutor js = (JavascriptExecutor) webDriver;


        try {
            new Thread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//      WebElement nextPage = webDriver.findElement(By.xpath("//*[@id=\"J_bottomPage\"]/span[1]/a[9]"));//滚动到下一页的位置并点击
        WebElement nextPage = webDriver.findElement(By.xpath("//*[@id=\"J_topPage\"]/a[2]"));//滚动到下一页的位置并点击
        nextPage.click();
        try {
            new Thread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement addToCart = webDriver.findElement(By.xpath("//*[@id=\"J_goodsList\"]/ul/li[3]/div/div[7]/a[3]"));//加入购物车
        addToCart.click();


    }
}
