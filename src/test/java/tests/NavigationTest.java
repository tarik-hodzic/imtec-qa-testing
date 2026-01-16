package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NavigationTest extends BaseTest {

    @Test
    public void MainNavigation() throws Exception {
        webDriver.get(BASE_URL);

        webDriver.findElement(By.xpath("//li[@id='category-16']/a")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//ol/li/span"))
        );

        String headingText = heading.getText().trim().toLowerCase();
        assertTrue(headingText.equalsIgnoreCase("mobiteli"));
    }

    @Test
    public void DropDownNavigation() throws Exception {
        webDriver.get(BASE_URL);

        WebElement laptopiMenu = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(normalize-space(), 'Laptopi')]")
                ));

        Actions actions = new Actions(webDriver);
        actions.moveToElement(laptopiMenu).build().perform();
        Thread.sleep(500);

        WebElement tabletiLink = webDriver.findElement(By.xpath("//li[@id='category-162']/a"));
        tabletiLink.click();
        Thread.sleep(500);

        WebElement heading = webDriver.findElement(By.tagName("h1"));
        String headingText = heading.getText().trim().toLowerCase();
        assertTrue(headingText.contains("tableti"));
    }

    @Test
    public void NavigationSales() throws Exception {
        webDriver.get(BASE_URL);

        webDriver.findElement(By.xpath("//a[contains(@href, '/cms/imtec-akcija')]")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ol")));

        List<WebElement> items = webDriver.findElements(By.xpath("//ol/li"));

        boolean found = false;
        for (WebElement li : items) {
            String text = li.getText().trim().toLowerCase();
            if (text.contains("akcija")) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }
}
