package TestNGtest.dataProdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestTestNGProvider {
	WebDriver driver;
	@DataProvider(name = "searchWords")
	public static Object[][] words(){
		return new Object[][] {{"青瓷","主演","王志文"},{"唐人街探案","主演","王宝强"},{"我说的都是真的","主演","小沈阳"}};
	}
	@Test(dataProvider = "searchWords")
	public void test(String searchWord1, String searchWord2, String searchResult) {
		driver = new ChromeDriver();
		//设定等待时间为10s
		driver.get("https://www.baidu.com");
		driver.findElement(By.id("kw")).sendKeys(searchWord1 + " " + searchWord2);
		//判断搜索结果中是否包含测试数据中期望的关键字
		Assert.assertFalse(driver.getPageSource().contains(searchResult));
	}

	@AfterMethod
	public void after(){
		driver.quit();
	}
}
