package StepDefanation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JumiaHomePage {
    private WebDriver driver;

    public JumiaHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToJumiaWebsite() {
        driver.get("https://www.jumia.com.eg/");
    }

    public void clickSignInLink() {
        driver.findElement(By.linkText("Sign in")).click();
    }

    public JumiaRegistrationPage navigateToRegistrationPage() {
        driver.findElement(By.linkText("Create an account")).click();
        return new JumiaRegistrationPage(driver);
    }

    public JumiaLoginPage navigateToLoginPage() {
        driver.findElement(By.linkText("Sign in")).click();
        return new JumiaLoginPage(driver);
    }

    public void addToCart(String itemName, String itemPrice) {
        WebElement addToCartButton = driver.findElement(By.xpath("//h3[contains(text(),'" + itemName + "')]/following-sibling::div[contains(@class,'add-to-cart')]//a[contains(@class,'btn-add-to-cart')]"));
        addToCartButton.click();
    }


    public JumiaCartPage navigateToCartPage() {
        WebElement cartIcon = driver.findElement(By.cssSelector(".nav-cart .basket"));
        cartIcon.click();
        return new JumiaCartPage(driver);
    }

}

