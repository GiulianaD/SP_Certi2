import com.google.common.collect.Ordering;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BaseTest {

    @Test
    public void sortProductsByPriceDescendingTest() {
        login();

        WebElement sortingFilter = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container")));
        Select selectObject = new Select(sortingFilter);
        selectObject.selectByValue("hilo");

        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        List<Float> actualPrices = new ArrayList<>();

        for(WebElement price : prices) {
            Float numericPrice = Float.parseFloat(price.getText().replaceFirst("\\$", ""));
            actualPrices.add(numericPrice);
        }

        boolean isSorted = Ordering.natural().reverse().isOrdered(actualPrices);
        Assertions.assertTrue(isSorted);
    }
}
