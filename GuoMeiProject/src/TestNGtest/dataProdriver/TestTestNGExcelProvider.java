package TestNGtest.dataProdriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestTestNGExcelProvider {
	WebDriver driver;
	String baseUrl = "https://www.baidu.com";
	//使用注解 DataProvider，将数据集合命名为 testData
		@DataProvider(name = "testData")
		public  Object[][] words() throws IOException{
			//调用 getData 方法获取 Excel 文件的测试数据
			return getTestData("/var/lib/jenkins/Desktop/","testData.xlsx","sheet1");
		}
		@Test(dataProvider = "testData")
		public void test(String seachWord1, String searchWord2, String searchResult) {
			driver.get(baseUrl);
			//使用 Excel文件中每行的前两个值作为搜索词，在两个搜索词中间加一个空格
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
		public Object[][] getTestData(String filePath, String fileName, String sheetName) throws IOException{
			//根据参数传入的文件路径和文件名称，组合出 Excel 文件的绝对路径
			//声明一个 File 对象
			File file = new File(filePath + "/" + fileName);
			//创建 FileInputStream 对象用于读取 Excel 文件
			FileInputStream inputStream = new FileInputStream(file);
			//声明 WorkBook 对象
			Workbook workBook = null;
			//获取文件名的扩展名，判断是.xlsx文件还是.xls 文件
			String fileExtensionName = fileName.substring(fileName.indexOf("."));
			//判断如果文件类型是.xlsx，则使用 XSSFWorkbook 对象进行实例化
			//如果是.xls，则使用 HSSFWorkbook 对象进行实例化
			if(fileExtensionName.equals(".xlsx")) {
				workBook = new XSSFWorkbook(inputStream);
			}else if(fileExtensionName.equals(".xls")) {
				workBook = new HSSFWorkbook(inputStream);
			}
			//通过 sheetName 参数，生成 Sheet 对象
			Sheet sheet = workBook.getSheet(sheetName);
			//获取Excel 数据文件 sheet1中的数据的行数，getLastRowNum 方法获取数据的最后一行行号
			//getFirstRowNum 获取数据的第一行行号
			//Excel 文件的行号和列号都是从0开始的
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			//创建名为 records 的List 对象来存储 Excel 文件读取的数据
			List<Object[]> records = new ArrayList<Object[]>();
			//使用 for 循环遍历 Excel 文件的所有数据（第一行除外，第一行为字段名称）
			//所以 i 从1开始，而不是从0开始
			for(int i = 1; i<rowCount +1;i++) {
				//使用 getRow 方法获取行对象
				Row row = sheet.getRow(i);
				//声明一个数组，用来存储 Excel 文件每行中的数据
				//数组的大小用 getLastCellNum 办法来进行动态声明，实现测试数据与数组大小一致
				String fields[] = new String[row.getLastCellNum()];
				for(int j = 0;j < row.getLastCellNum(); j++) {
					//调用 getCell 和 getStringCellValue 方法获取 Excel 文件中的单元格数据
					fields[j] = row.getCell(j).getStringCellValue();
				}
				//将 fields 数组存储到 records 的 list 中
				records.add(fields);
			}
			//定义方法返回值，Object[][],将 records  list 转换为一个 Object 的二维数组
			Object[][] results = new Object[records.size()][];
			//设置二维数组每行的值
			for(int i = 0; i<records.size();i++) {
				results[i] = records.get(i);
			}
			return results;
			
		}
}
