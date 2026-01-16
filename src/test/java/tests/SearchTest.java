package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest extends BaseTest {

    @Test
    public void SearchExistingProduct() {
        webDriver.get(BASE_URL);

        WebElement searchBox = webDriver.findElement(By.name("s"));
        searchBox.clear();
        searchBox.sendKeys("Mobitel Samsung Galaxy A56 8/256GB, crni", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement productLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h2.product-title a")
                )
        );

        assertEquals(
                "Mobitel Samsung Galaxy A56 8/256GB, crni",
                productLink.getText().trim(),
                "Očekivali smo da prvi rezultat bude 'Apple Iphone 16 128GB black (6060)'."
        );
    }

    @Test
    public void SearchPartialName() throws Exception {
        webDriver.get(BASE_URL);

        WebElement searchBox = webDriver.findElement(By.name("s"));
        searchBox.clear();
        searchBox.sendKeys("mon", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h2.product-title a")
        ));

        List<WebElement> results =
                webDriver.findElements(By.cssSelector("h2.product-title a"));

        assertFalse(results.isEmpty(), "Search za 'mon' nije vratio nijedan rezultat.");

        boolean monitorFound = false;
        for (WebElement product : results) {
            String title = product.getText().toLowerCase();
            if (title.contains("monitor")) {
                monitorFound = true;
                break;
            }
        }

        assertTrue(
                monitorFound,
                "Očekivali smo da među rezultatima za 'mon' postoji barem jedan monitor."
        );
    }

    @Test
    public void SearchNonExistingProduct() throws Exception {
        webDriver.get(BASE_URL);

        WebElement searchBox = webDriver.findElement(By.name("s"));
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys("asdasdasd123");
        searchBox.sendKeys(Keys.ENTER);

        WebElement headingDiv = webDriver.findElement(By.xpath("//*[@id=\"js-product-list-top\"]/div[1]/p"));
        String heading = headingDiv.getText();
        String expectedText = "Još uvijek nema dostupnih proizvoda";

        assertEquals(
                expectedText,
                heading,
                "Poruka za nepostojeći proizvod nije prikazana."
        );
    }

    @Test
    public void SearchWithSpecialCharacters() throws Exception {
        webDriver.get(BASE_URL);

        WebElement searchBox = webDriver.findElement(By.name("s"));
        searchBox.clear();
        searchBox.sendKeys("@@@###", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement noProductsMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".page-content.page-not-found h4")
                )
        );

        assertEquals(
                "Još uvijek nema dostupnih proizvoda",
                noProductsMessage.getText().trim(),
                "Poruka za nepostojeći proizvod nije ispravno prikazana."
        );
    }

    @Test
    public void EmptySearch() throws Exception {
        webDriver.get(BASE_URL);

        WebElement searchBox = webDriver.findElement(By.name("s"));
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement noProductsMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".page-content.page-not-found h4")
                )
        );

        assertEquals(
                "Još uvijek nema dostupnih proizvoda",
                noProductsMessage.getText().trim(),
                "Poruka za praznu pretragu nije ispravno prikazana."
        );
    }

    @Test
    public void RelatedMultiKeywordSearch() throws Exception {
        webDriver.get(BASE_URL);

        WebElement searchBox = webDriver.findElement(By.name("s"));
        searchBox.clear();
        searchBox.sendKeys("laptop lenovo", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        List<WebElement> titles = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("h2.product-title a")
                )
        );

        boolean found = false;
        for (WebElement t : titles) {
            String text = t.getText().toLowerCase();
            if (text.contains("laptop") || text.contains("lenovo")) {
                found = true;
                break;
            }
        }

        assertTrue(found, "Nema rezultata koji sadrže 'laptop' ili 'lenovo'.");
    }

    @Test
    public void UnrelatedMultiKeywordSearch() throws Exception {
        webDriver.get(BASE_URL);

        WebElement searchBox = webDriver.findElement(By.name("s"));
        searchBox.clear();
        searchBox.sendKeys("laptop televizor", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement noProductsMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".page-content.page-not-found h4")
                )
        );

        assertEquals(
                "Još uvijek nema dostupnih proizvoda",
                noProductsMessage.getText().trim(),
                "Poruka za nepostojeći proizvod nije prikazana."
        );
    }
}
