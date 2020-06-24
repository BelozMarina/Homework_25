package homework_25;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TestAutomationPracticeSIte extends BaseTest {

    @Test(dataProvider = "invalidCredentials")
    public void typeLogin(String email, String password) {

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
        String error = driver.findElement(By.xpath("//*[@id='center_column']/div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("There is 1 error\n" +
                "Invalid email address.", error);
    }

    @Test(dataProvider = "validCredentials")
    public void typeLogin1(String email, String password) {

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
        String error = driver.findElement(By.xpath("//*[@id='center_column']/div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("There is 1 error\n" +
                "Authentication failed.", error);
    }

//    @Test
//    public void notFoundToElement() {
//        driver.findElement(By.className("blockbestsellers")).click();
//    }

    @DataProvider
    public Iterator<Object[]> invalidCredentials() {
        List<Object[]> data = new ArrayList<Object[]>();
        for (int i = 0; i < 3; i++) {
            data.add(new Object[]{
                    generateRandomName(), generateRandomPassword()
            });
        }
        return data.iterator();
    }

    private Object generateRandomName() {
        return "name@" + new Random().nextInt();
    }

    private Object generateRandomPassword() {
        return "password" + new Random().nextInt();
    }

    @DataProvider
    public Object[][] validCredentials() {
        return new Object[][] {
                {"admin@gmail.com", "qwerty"},
                {"user@test.com", "987654"},
        };
    }
}
