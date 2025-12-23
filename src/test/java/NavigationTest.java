import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NavigationTest {

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
    public void MainNavigation() throws Exception {
        driver.get("https://imtec.ba/");

        driver.findElement(By.xpath("//li[@id='category-16']/a")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//ol/li/span"))
        );

        String headingText = heading.getText().trim().toLowerCase();
        assertTrue(headingText.equalsIgnoreCase("mobiteli"));
    }

    @Test
    public void DropDownNavigation() throws Exception {
        driver.get("https://imtec.ba/");

        WebElement laptopiMenu = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(normalize-space(), 'Laptopi')]")
                ));

        Actions actions = new Actions(driver);
        actions.moveToElement(laptopiMenu).build().perform();
        Thread.sleep(500);

        WebElement tabletiLink = driver.findElement(By.xpath("//li[@id='category-162']/a"));
        tabletiLink.click();
        Thread.sleep(500);

        WebElement heading = driver.findElement(By.tagName("h1"));
        String headingText = heading.getText().trim().toLowerCase();
        assertTrue(headingText.contains("tableti"));
    }

    @Test
    public void NavigationSales() throws Exception {
        driver.get("https://imtec.ba/");

        driver.findElement(By.xpath("//a[contains(@href, '/cms/imtec-akcija')]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ol")));

        List<WebElement> items = driver.findElements(By.xpath("//ol/li"));

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
