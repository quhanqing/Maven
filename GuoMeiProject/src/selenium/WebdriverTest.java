package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class WebdriverTest {

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/chromedriver");
        WebDriver driver=new ChromeDriver();
        driver.get("http://www.w3school.com.cn/js/index.asp");








      /*  WebElement draggable=driver.findElement(By.id("draggable"));
        for(int i=0;i<5;i++){
            new Actions(driver).dragAndDropBy(draggable,0,10);  //向下拖动10个像素，共拖动5次
        }
        for(int i=0;i<5;i++){
            new Actions(driver).dragAndDropBy(draggable,10,0);  //向右拖动10个像素，共拖动5次
        }

       */

        //JavascriptExecutor js=(JavascriptExecutor)driver;
       // Object length=js.executeScript("return document.body.outerHTML.length");
       // System.out.println(length.toString());

        //WebElement e=driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[10]/a/img"));
       // js.executeAsyncScript("arguments[0].scrollIntoView(true)",e);//滚动到e元素出现的位置

        //js.executeScript("document.documentElement.scrollTop=10000");//如果想滚动到底端，可把scrolltop设置为很大




        // WebElement fruit= driver.findElement(By.xpath("//select[@name='fruit']"));
       // Select select=new Select(fruit);
       // String firstFruitName=select.getFirstSelectedOption().getText();//  获取当前被选中的下拉列表选项文本
        //Select list=new Select(driver.findElement(By.name("fruit")));
        /*List<WebElement> fruits=driver.findElements(By.name("fruit"));
        for(WebElement fruit1:fruits){
            if (fruit1.getAttribute("value").equals("watermelon")) {

                if(!fruit1.isSelected()){                                  //判断单选框是否被选中
                    fruit1.click();
                }
            }

        }

         */

        //list.selectByIndex(3);
        //list.selectByValue("shanzha");
        //Actions action=new Actions(driver);
       // WebElement inputBox=driver.findElement(By.id("inputBox"));
       // action.doubleClick(inputBox).build().perform();

        //将光标定位到百度搜索输入框
        //driver.findElement(By.id("kw")) .sendKeys("私人定制");
        //输入私人定制
        /*WebElement address=driver.findElement(By.name("address"));
        address.sendKeys("121213223@qq.com");
        WebElement username=driver.findElement(By.id("username"));
        username.sendKeys("1111111");
        WebElement pwd=driver.findElement(By.id("password"));
        pwd.sendKeys("2222222");
        WebElement login=driver.findElement(By.id("submit"));
        login.click();
        WebElement link=driver.findElement(By.partialLinkText("百度一下"));
        System.out.println(link.getText());

        */
        //*[@id="div1"]/input[2]  //标签名[@属性=值]
       //String pageSource= driver.getPageSource();
       //System.out.println(pageSource);

        //driver.quit();
    }
}


