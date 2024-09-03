import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SideBarTest extends BaseTest{

    public void openSideBar() {
        WebElement sideBarButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn")));
        sideBarButton.click();
    }

    @Test
    public void logOutTest(){
        login();
        openSideBar();

        WebElement logoutLink = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        logoutLink.click();

        WebElement loginLogo = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("login_logo")));
        Assertions.assertTrue(loginLogo.isDisplayed());
    }

    public void selectProductButtons(int numberButtons) {
        List<WebElement> buttons = driver.findElements(By.className("btn_inventory"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < numberButtons; i++) {
            js.executeScript("arguments[0].scrollIntoView(true)", buttons.get(i));
            buttons.get(i).click();
        }
    }

    @Test
    public void resetAppTest(){
        login();
        selectProductButtons(3);
        openSideBar();

        WebElement resetAppStateButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("reset_sidebar_link")));
        resetAppStateButton.click();

        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_link"));
        Assertions.assertEquals(shoppingCart.getText(), "");

        List<WebElement> buttons = driver.findElements(By.className("btn_inventory"));

        for (WebElement button : buttons) {
            Assertions.assertEquals(button.getText(), "Add to cart");
        }
    }

    @Test
    public void aboutLinkTest(){
        login();
        openSideBar();

        WebElement aboutLink = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link")));
        aboutLink.click();

        String expectedURL = "https://saucelabs.com/";
        Assertions.assertEquals(expectedURL, driver.getCurrentUrl());
    }
}
