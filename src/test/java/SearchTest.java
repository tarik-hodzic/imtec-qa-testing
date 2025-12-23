import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchTest {

    private WebDriver driver;

    @BeforeAll
    void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
        System.out.println("DriverManager initialized.");
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser started.");
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }

    @AfterAll
    void cleanup() {
        System.out.println("All tests finished.");
    }

    @Test
    public void SearchExistingProduct() {
        driver.get("https://imtec.ba/");

        WebElement searchBox = driver.findElement(By.name("s"));
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys("Apple Iphone 16 128GB black (6060)");
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h2.product-title a")
                )
        );
        assertEquals(
                "Apple Iphone 16 128GB black (6060)",
                productLink.getText().trim(),
                "Očekivali smo da prvi rezultat bude 'Apple Iphone 16 128GB black (6060)'."
        );
    }

    @Test
    public void SearchPartialName() throws Exception {
        driver.get("https://imtec.ba/");

        WebElement searchBox = driver.findElement(By.name("s"));
        searchBox.clear();
        searchBox.sendKeys("mon");
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h2.product-title a")
        ));

        List<WebElement> results = driver.findElements(By.cssSelector("h2.product-title a"));

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
                "Očekivali smo da među rezultatima za 'mon' postoji barem jedan monitor, " +
                        "ali nijedan naslov ne sadrži riječ 'monitor'."
        );
    }



    @Test
    public void SearchNonExistingProduct() throws Exception {
        driver.get("https://imtec.ba/");

        WebElement searchBox = driver.findElement(By.name("s"));
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys("asdasdasd123");
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement noProductsMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".page-content.page-not-found h4")
                )
        );

        String expectedText = "Još uvijek nema dostupnih proizvoda";
        String actualText = noProductsMessage.getText().trim();

        assertEquals(
                expectedText,
                actualText,
                "Poruka za nepostojeći proizvod nije prikazana."
        );
    }


    @Test
    public void SearchWithSpecialCharacters() throws Exception {
        driver.get("https://imtec.ba/");

        WebElement searchBox = driver.findElement(By.name("s"));
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys("@@@###");
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement noProductsMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".page-content.page-not-found h4")
                )
        );

        String expectedText = "Još uvijek nema dostupnih proizvoda";
        String actualText = noProductsMessage.getText().trim();

        assertEquals(
                expectedText,
                actualText,
                "Poruka za praznu pretragu nije ispravno prikazana."
        );
    }

    @Test
    public void EmptySearch() throws Exception {
        driver.get("https://imtec.ba/");

        WebElement searchBox = driver.findElement(By.name("s"));
        searchBox.click();
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement noProductsMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".page-content.page-not-found h4")
                )
        );

        String expectedText = "Još uvijek nema dostupnih proizvoda";
        String actualText = noProductsMessage.getText().trim();

        assertEquals(
                expectedText,
                actualText,
                "Poruka za praznu pretragu nije ispravno prikazana."
        );
    }


   /* @Test
    public void SearchWithSpaces() throws Exception {
        driver.get("https://imtec.ba/");

        WebElement searchBox = driver.findElement(By.name("s"));
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys("  laptop ");
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("ul.products li.product")
        ));

        List<WebElement> productTitles = driver.findElements(
                By.cssSelector("h2.product-title a")
        );

        assertFalse(
                productTitles.isEmpty(),
                "Očekivali smo da 'laptop' vrati neke rezultate."
        );

        boolean foundLaptop = false;

        for (WebElement title : productTitles) {
            if (title.getText().toLowerCase().contains("laptop")) {
                foundLaptop = true;
                break;
            }
        }

        assertTrue(
                foundLaptop,
                "Nijedan rezultat ne sadrži riječ 'laptop' u naslovu."
        );
    }

*/


    @Test
    public void RelatedMultiKeywordSearch() throws Exception {
        driver.get("https://imtec.ba/");

        WebElement searchBox = driver.findElement(By.name("s"));
        searchBox.clear();
        searchBox.sendKeys("laptop lenovo", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        driver.get("https://imtec.ba/");

        WebElement searchBox = driver.findElement(By.name("s"));
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys("laptop televizor");
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement noProductsMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".page-content.page-not-found h4")
                )
        );

        String expectedText = "Još uvijek nema dostupnih proizvoda";
        String actualText = noProductsMessage.getText().trim();

        assertEquals(
                expectedText,
                actualText,
                "Poruka za nepostojeći proizvod nije prikazana."
        );
    }

}
