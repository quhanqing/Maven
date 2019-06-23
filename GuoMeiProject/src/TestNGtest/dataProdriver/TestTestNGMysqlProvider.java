package TestNGtest.dataProdriver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestTestNGMysqlProvider {
	WebDriver driver;
	String baseUrl = "https://www.baidu.com";
	//使用注解 DataProvider，将数据集合命名为 testData
		@DataProvider(name = "testData")
		public  Object[][] words() throws IOException{
			//调用 getData 方法获取 MySQL 数据库 testData 表的测试数据
			return getTestData("testData");
		}
		@Test(dataProvider = "testData")
		public void test(String seachWord1, String searchWord2, String searchResult) {
//			driver.get(baseUrl);
			//使用 Excel文件中每行的前两个值作为搜索词，在两个搜索词中间加一个空格
//			driver.findElement(By.id("kw")).sendKeys(seachWord1 + " " + searchWord2);
//			Assert.assertTrue(driver.getPageSource().contains(searchResult));
		}
		@BeforeMethod
		public void beforeMethod() {
//			driver = new SafariDriver();
		}
		@AfterMethod
		public void afterMethod() {
//			driver.quit();
		}
		public Object[][] getTestData(String tableName) throws IOException{
			//声明 MySQL 数据库的驱动
			String mysqlDriver = "com.mysql.jdbc.Driver";
			//声明数据库IP 地址/端口号和数据库名称
			String url = "jdbc:mysql://127.0.0.1:3306/testng";
			//声明数据库的账号，密码
			String user = "root";
			String password = "123456";
			//声明存储测试数据的 List 对象
			List<Object[]> records = new ArrayList<Object[]>();
			try {
				//设定驱动
				Class.forName(mysqlDriver);
				//声明连接数据库的链接对象，使用数据库服务器地址、用户名、密码作为参数
				Connection conn = DriverManager.getConnection(url, user, password);
				if(!conn.isClosed())
					System.out.println("连接数据库成功");
				//创建 statement 对象
				Statement statement = conn.createStatement();
				//构造sql 查询语句
				String sql = "select * from " + tableName;
				//声明 ResultSet 对象，用来存储 sql 语句执行后返回的结果集
				ResultSet rs = statement.executeQuery(sql);
				//声明一个 ResultSetMetaData 对象
				ResultSetMetaData rsMetaData = rs.getMetaData();
				//调用ResultSetMetaData对象的 getColumnCount 方法获取结果集的列数
				int cols = rsMetaData.getColumnCount();
				//使用 next 方法遍历结果集中的所有数据
				while(rs.next()) {
					//声明一个数组，数组的大小使用结果集的列数声明
					String fields[] = new String[cols];
					int col = 0;
					//遍历所有数据行中的所有列数据，并储存在数组中
					for(int colIdex = 0; colIdex < cols; colIdex++) {
						fields[col] = rs.getString(colIdex + 1);
						col++;
					}
					//将数组存储到 records 中
					records.add(fields);
					System.out.println(rs.getString(1) );
				}
				//关闭结果集对象
				rs.close();
				//关闭数据库连接
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
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
