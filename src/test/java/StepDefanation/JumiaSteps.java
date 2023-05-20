package StepDefanation;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class JumiaSteps {
    private WebDriver driver;
    private JumiaHomePage homePage;
    private JumiaRegistrationPage registrationPage;
    private JumiaLoginPage loginPage;
    private JumiaCartPage cartPage;

    @Given("I am on Jumia website")
    public void navigateToJumiaWebsite() {
        driver = new ChromeDriver();
        homePage = new JumiaHomePage(driver);
        homePage.navigateToJumiaWebsite();
    }

    @Given("I click on the Account -> Sign in link")
    public void clickSignInLink() {
        homePage.clickSignInLink();
    }

    @When("I fill in all the registration data")
    public void fillRegistrationData(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String name = data.get(0).get("Name");
        String email = data.get(0).get("Email");
        String password = data.get(0).get("Password");
        registrationPage = homePage.navigateToRegistrationPage();
        registrationPage.enterRegistrationData(name, email, password);
        registrationPage.submitRegistrationData();
    }

    @Then("I should be able to login using the newly created account")
    public void loginUsingNewlyCreatedAccount(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String email = data.get(0).get("Email");
        String password = data.get(0).get("Password");
        loginPage = homePage.navigateToLoginPage();
        loginPage.login(email, password);
    }

    @When("I hover on Fashion -> Shirts")
    public void hoverOnShirts() {
        Actions actions = new Actions(driver);
        WebElement fashionMenu = driver.findElement(By.cssSelector("#main-menu li:nth-child(2)"));
        WebElement shirtsSubMenu = driver.findElement(By.cssSelector("#main-menu li:nth-child(2) .submenu li:nth-child(3)"));
        actions.moveToElement(fashionMenu).moveToElement(shirtsSubMenu).click().build().perform();
    }

    @When("I add two items to my cart")
    public void addItemsToCart(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < 2; i++) {
            String itemName = data.get(i).get("ItemName");
            WebElement item = driver.findElement(By.xpath("//h3[contains(text(),'" + itemName + "')]/following-sibling::div[@class='prices']//span[@class='price']"));
            String itemPrice = item.getText();
            homePage.addToCart(itemName, itemPrice);
        }
    }

    @When("I click on the Cart button")
    public void clickOnCartButton() {
        cartPage = homePage.navigateToCartPage();
    }

    @Then("I should see the two items in my cart")
    public void verifyCartItems(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < 2; i++) {
            String expectedItemName = data.get(i).get("ItemName");
            String expectedItemPrice = data.get(i).get("ItemPrice");
            assertTrue(cartPage.isItemPresentInCart(expectedItemName, expectedItemPrice));
        }
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}

