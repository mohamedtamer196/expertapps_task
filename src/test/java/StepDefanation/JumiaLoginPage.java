package StepDefanation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JumiaLoginPage {
    private WebDriver driver;

    public JumiaLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }
}

