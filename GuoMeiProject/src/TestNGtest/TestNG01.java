package TestNGtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TestNG01 {

    @AfterMethod
    public void teardown(){
        System.out.println("i am  after method");
        //每个@Test即每个测试用例 后面 执行
    }
    @BeforeMethod
    public void init(){
        System.out.println("i am before method");
        //每个@Test即每个测试用例 前面 执行
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("i am  beforeClass method");
        //整体最前面运行beforeClass，比如全局隐士等待
    }
    @AfterClass
    public void AfterClass(){
        System.out.println("i am  afterClass method");
        //整体最后面运行afterClass
    }


    @Test
    //为方法添加注解，方法永远是void（因为是测试用例）有两个@Test，就是两个测试用例
    public void testAdd(){
        System.out.println(" I am test01");
    }
    @Test
    public void test(){
        System.out.println(" I am test02");
    }

}
