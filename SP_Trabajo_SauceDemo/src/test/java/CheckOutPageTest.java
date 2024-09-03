import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPageTest extends BaseTest {

    public void getToCheckoutPage() {
        login();
        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_link"));
        shoppingCart.click();

        WebElement checkoutButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout_button")));
        checkoutButton.click();
    }

    @Test
    public void emptyCheckoutDataTest() {
        getToCheckoutPage();

        WebElement firstNameTextBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        firstNameTextBox.sendKeys(" ");

        WebElement lastNameTextBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        lastNameTextBox.sendKeys(" ");

        WebElement zipCodeTextBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        zipCodeTextBox.sendKeys(" ");

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        WebElement errorBox =  new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("error-message-container")));
        Assertions.assertTrue(errorBox.isDisplayed());
    }
}
