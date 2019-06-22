package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class WebJD2 {
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/chromedriver");
        WebDriver webDriver=new ChromeDriver();
        webDriver.get("https://www.jd.com/");
        try {
            new Thread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions action=new Actions(webDriver);
        WebElement electarcalEquipment=webDriver.findElement(By.xpath("//*[@id=\"J_cate\"]/ul/li[1]/a"));
        action.moveToElement(electarcalEquipment).perform();
        try {
            new Thread().sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement tv=webDriver.findElement(By.xpath("//*[@id=\"cate_item1\"]/div[1]/div[2]/dl[1]/dd/a[1]"));
        tv.click();
        try {
            new Thread().sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement sony=webDriver.findElement(By.xpath("//*[@id=\"brand-18374\"]/a"));
        sony.click();
        try {
            new Thread().sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement car=webDriver.findElement(By.xpath("//*[@id=\"plist\"]/ul/li[1]/div/div[7]/a[3]"));
        car.click();
    }
}
