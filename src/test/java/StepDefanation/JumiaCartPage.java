package StepDefanation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class JumiaCartPage {
    private WebDriver driver;

    public JumiaCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void increaseItemQuantity(int index, int quantity) {
        WebElement qtyField = driver.findElement(By.cssSelector(String.format("input[name='quantity[%d]']", index)));
        qtyField.clear();
        qtyField.sendKeys(String.valueOf(quantity));
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public boolean isItemAddedToCart(String itemName) {
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-items .title a"));
        for (WebElement item : cartItems) {
            if (item.getText().contains(itemName)) {
                return true;
            }
        }
        return false;
    }

    public double getSubtotal() {
        String subtotalText = driver.findElement(By.cssSelector(".cart-items .subtotal span")).getText();
        return Double.parseDouble(subtotalText.replaceAll("[^\\d.]", ""));
    }

    public boolean isItemPresentInCart(String expectedItemName, String expectedItemPrice) {
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".sku.-gallery"));
        for (WebElement item : cartItems) {
            String itemName = item.findElement(By.cssSelector(".title")).getText();
            String itemPrice = item.findElement(By.cssSelector(".price-container .price")).getText();
            if (itemName.equals(expectedItemName) && itemPrice.equals(expectedItemPrice)) {
                return true;
            }
        }
        return false;
    }

}



