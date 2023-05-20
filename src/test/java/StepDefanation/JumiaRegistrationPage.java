package StepDefanation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JumiaRegistrationPage {
    private WebDriver driver;

    public JumiaRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterRegistrationData(String name, String email, String password) {
        driver.findElement(By.id("first_name")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password_confirmation")).sendKeys(password);
    }

    public void submitRegistrationData() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }
}

