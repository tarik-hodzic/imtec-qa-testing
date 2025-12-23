import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FooterTest {

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
    void footerIsPresentOnAllPages() throws InterruptedException {

        String[] pages = {
                "https://imtec.ba/",
                "https://imtec.ba/monitori",
                "https://imtec.ba/laptopi",
                "https://imtec.ba/prijava?back=my-account",
                "https://imtec.ba/registracija",
                "https://imtec.ba/politika-privatnosti"

        };

        for (String page : pages) {

            driver.get(page);
            Thread.sleep(1500);
            WebElement footer = driver.findElement(By.tagName("footer"));
            assertTrue(footer.isDisplayed(), "Footer is not visible on page: " + page);
        }
    }

    @Test
    void footerCompanyInformation() throws InterruptedException {

        driver.get("https://imtec.ba/");
        Thread.sleep(1500);

        WebElement footer = driver.findElement(By.id("contact-infos"));
        String footerText = footer.getText().toLowerCase();

        assertTrue(footerText.contains("ilijaš"));
        assertTrue(footerText.contains("bosna"));
        assertTrue(footerText.contains("@"));
        assertTrue(footerText.contains("+387"));
    }


    @Test
    void footerSocialMediaLinksExistence() {

        driver.get("https://imtec.ba/");

        assertNotNull(driver.findElement(By.cssSelector("a[aria-label='Facebook']")).getAttribute("href"));
        assertNotNull(driver.findElement(By.cssSelector("a[aria-label='Instagram']")).getAttribute("href"));
        assertNotNull(driver.findElement(By.cssSelector("a[aria-label='Linkedin']")).getAttribute("href"));
        assertNotNull(driver.findElement(By.cssSelector("a[aria-label='Youtube']")).getAttribute("href"));
    }

    @Test
    void footerLinks() throws InterruptedException {
        driver.get("https://imtec.ba/");
        driver.findElement(By.id("link-cms-page-7-1")).click();
        Thread.sleep(1500);
        WebElement headingDiv = driver.findElement(By.xpath("//*[@id=\"main\"]/header/h1"));
        String heading = headingDiv.getText();
        assertTrue(heading.contains("O nama"));
    }

}