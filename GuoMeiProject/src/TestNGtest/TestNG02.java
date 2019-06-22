package TestNGtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG02 {


    public int add(int num1, int num2) {
        return num1 + num2;
    }

    @Test
    public void testAdd() {

        int num1 = 99;
        int num2 = 1;
        int result = add(num1, num2);
        Assert.assertEquals(result, 100); //TestNG 断言 result预期结果101
    }
}
