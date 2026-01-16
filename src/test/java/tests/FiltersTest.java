package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FiltersTest extends BaseTest {

    @Test
    void sortingPrice() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.xpath("//*[@id=\"category-83\"]/a")).click();
        webDriver.findElement(By.xpath("//div[@id='js-product-list-top']/div[2]/div/div/button")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.linkText("Cijena, uzlazno")).click();
        Thread.sleep(2000);

        List<WebElement> products =
                webDriver.findElements(By.cssSelector(".js-product.product.col-xs-6.col-sm-6.col-xl-4"));

        WebElement firstProduct = products.get(0);
        WebElement secondProduct = products.get(1);

        String firstPriceText = firstProduct.findElement(By.className("price")).getText();
        String secondPriceText = secondProduct.findElement(By.className("price")).getText();

        double firstPrice = Double.parseDouble(
                firstPriceText.replace(".", "").replace(",", ".").replace("KM", "").trim()
        );

        double secondPrice = Double.parseDouble(
                secondPriceText.replace(".", "").replace(",", ".").replace("KM", "").trim()
        );

        assertTrue(firstPrice <= secondPrice, "First product should be more/equal expensive");
    }

    @Test
    void brandFiltering() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.xpath("//li[@id='category-83']/a")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement selectEl = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("PM_ASCriterionGroupSelect_15_12")
                )
        );

        new Select(selectEl).selectByValue("1029");
        Thread.sleep(2000);

        List<WebElement> products =
                webDriver.findElements(By.cssSelector(".js-product.product.col-xs-6.col-sm-6.col-xl-4"));

        for (WebElement product : products) {
            String productText = product.getText().toLowerCase();
            assertTrue(
                    productText.contains("acer"),
                    "Product does not contain Acer: " + productText
            );
        }
    }

    @Test
    void multipleFilters() throws InterruptedException {
        webDriver.get("https://imtec.ba/monitori");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement brandSelect = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("PM_ASCriterionGroupSelect_15_12")
                )
        );
        new Select(brandSelect).selectByValue("1029");
        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".js-product.product.col-xs-6.col-sm-6.col-xl-4")
        ));

        WebElement sizeSelect = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("PM_ASCriterionGroupSelect_15_16")
                )
        );
        new Select(sizeSelect).selectByValue("1356");
        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".js-product.product.col-xs-6.col-sm-6.col-xl-4")
        ));

        List<WebElement> products =
                webDriver.findElements(By.cssSelector(".js-product.product.col-xs-6.col-sm-6.col-xl-4"));

        for (WebElement product : products) {
            String text = product.getText().toLowerCase();
            assertTrue(
                    text.contains("acer") && text.contains("27''"),
                    "Product does not match filters: " + text
            );
        }
    }

    @Test
    void filtersAndSortingPrice() throws InterruptedException {
        webDriver.get("https://imtec.ba/monitori");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement brandSelect = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("PM_ASCriterionGroupSelect_15_12"))
        );
        new Select(brandSelect).selectByValue("1029");
        Thread.sleep(1500);

        WebElement sizeSelect = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("PM_ASCriterionGroupSelect_15_16"))
        );
        new Select(sizeSelect).selectByValue("1356");
        Thread.sleep(1500);

        webDriver.findElement(By.cssSelector("#js-product-list-top button")).click();
        Thread.sleep(500);
        webDriver.findElement(By.linkText("Cijena, uzlazno")).click();
        Thread.sleep(2000);

        List<WebElement> products =
                webDriver.findElements(By.cssSelector(".js-product.product.col-xs-6.col-sm-6.col-xl-4"));

        String firstPriceText = products.get(0)
                .findElement(By.xpath(".//*[contains(text(),'KM')]"))
                .getText();

        String secondPriceText = products.get(1)
                .findElement(By.xpath(".//*[contains(text(),'KM')]"))
                .getText();

        double firstPrice = Double.parseDouble(
                firstPriceText.replace("KM", "").replace(".", "").replace(",", ".").trim()
        );

        double secondPrice = Double.parseDouble(
                secondPriceText.replace("KM", "").replace(".", "").replace(",", ".").trim()
        );

        assertTrue(firstPrice <= secondPrice, "Prices are not sorted ascending");

        for (WebElement product : products) {
            String text = product.getText().toLowerCase();
            assertTrue(text.contains("acer"), "Not Acer product: " + text);
            assertTrue(text.contains("27"), "Not 27 inch product: " + text);
        }
    }

    @Test
    void paginationPage() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.xpath("//li[@id='category-83']/a")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement selectEl = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("PM_ASCriterionGroupSelect_15_12")
                )
        );

        new Select(selectEl).selectByValue("1054");
        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector(".next.js-search-link")).click();
        Thread.sleep(2000);

        List<WebElement> productsPage2 =
                webDriver.findElements(By.cssSelector(".js-product.product.col-xs-6.col-sm-6.col-xl-4"));
        Thread.sleep(1000);

        assertTrue(productsPage2.size() > 0, "Page 2 shows no products");
    }
}
