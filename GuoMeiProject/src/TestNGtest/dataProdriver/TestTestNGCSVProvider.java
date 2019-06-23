package TestNGtest.dataProdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestTestNGCSVProvider {
	WebDriver driver;
	String baseUrl = "https://www.baidu.com";
	//使用注解 DataProvider，将数据集合命名为 testData
	@DataProvider(name = "testData")
	public  Object[][] words() throws IOException{
		return getTestData("/var/lib/jenkins/Desktop/testData.csv");
	}
	@Test(dataProvider = "testData")
	public void test(String seachWord1, String searchWord2, String searchResult) {
		driver.get(baseUrl);
		//使用 csv 文件中每行的前两个值作为搜索词，在两个搜索词中间加一个空格
		driver.findElement(By.id("kw")).sendKeys(seachWord1 + " " + searchWord2);
		Assert.assertTrue(driver.getPageSource().contains(searchResult));
	}
	@BeforeMethod
	public void beforeMethod() {
		driver = new SafariDriver();
	}
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	public Object[][] getTestData(String fileName) throws IOException{
		List<Object[]> records = new ArrayList<Object[]>();
		String record;
		//设定 utf-8字符集，读取文件内容
		BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"utf-8"));
		//跳过文件中的第一行
		file.readLine();
		/*
		 * 遍历读取文件中除第一行外的其他所有行内容
		 * 并储存在 records 的 arrayList 中
		 * 每一个 records 中存储的对象为一个 String 数组
		 */
		while((record = file.readLine()) != null) {
			String fields[] = record.split(",");
			records.add(fields);
		}
		file.close();
		//定义方法返回值，就是 Object[][];将存储测试数据的 List 转换为一个 Object 的二维数组
		Object[][] results = new Object[records.size()][];
		//设置二维数组每行的值
		for(int i = 0; i < records.size() ; i++) {
			results[i] = records.get(i);
		}
		return results;
	}
}
