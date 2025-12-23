import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SecurityTest {

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
    void httpsEnforcement() throws InterruptedException {
        driver.get("http://imtec.ba");
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("https"));
        driver.get("http://imtec.ba/mojracun");
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("https"));
        driver.get("http://imtec.ba/registracija");
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("https"));
        driver.get("http://imtec.ba/prijava?back=https%3A%2F%2Fimtec.ba%2Fregistracija");
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("https"));
    }

    @Test
    void protectedPage() throws InterruptedException {
        driver.get("https://imtec.ba/identitet");
        Thread.sleep(1000);
        WebElement headingDiv = driver.findElement(By.xpath("//*[@id=\"main\"]/header"));
        String heading = headingDiv.getText();
        assertEquals("Prijava u Vaš račun", heading);
    }


    @Test
    void logoutBackButton() throws InterruptedException {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a")).click();

        driver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        Thread.sleep(1000);
        driver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        Thread.sleep(1000);
        driver.findElement(By.id("submit-login")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div/a")).click();
        Thread.sleep(1000);
        driver.navigate().back();
        Thread.sleep(1000);
        WebElement headingDiv = driver.findElement(By.xpath("//*[@id=\"main\"]/header"));
        String heading = headingDiv.getText();
        assertEquals("Prijava u Vaš račun", heading);
    }

    @Test
    void logoutAfterCookieDeletion() throws InterruptedException {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a")).click();

        driver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        Thread.sleep(1000);
        driver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        Thread.sleep(1000);
        driver.findElement(By.id("submit-login")).click();
        Thread.sleep(1000);
        driver.manage().deleteAllCookies();
        Thread.sleep(500);
        driver.navigate().refresh();
        Thread.sleep(500);
        WebElement linkDiv = driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a"));
        String link = linkDiv.getText();
        assertTrue(link.contains("Prijavite se"));
    }

    @Test
    void logoutShouldAffectAllTabs() throws InterruptedException {

        // TAB 1
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a")).click();
        driver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        Thread.sleep(500);
        driver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        Thread.sleep(500);
        driver.findElement(By.id("submit-login")).click();
        Thread.sleep(1000);

        String tab1 = driver.getWindowHandle();
        ((JavascriptExecutor) driver).executeScript("window.open();");

        Set<String> allTabs = driver.getWindowHandles();

        String tab2 = "";
        for (String tab : allTabs) {
            if (!tab.equals(tab1)) {
                tab2 = tab;
            }
        }

        driver.switchTo().window(tab2);
        driver.get("https://imtec.ba/mojracun");
        Thread.sleep(2000);
        driver.switchTo().window(tab1);
        driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div/a")).click();

        Thread.sleep(2000);

        // TAB 2
        driver.switchTo().window(tab2);
        driver.navigate().refresh();
        Thread.sleep(2000);
        WebElement linkDiv = driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a"));
        String link = linkDiv.getText();
        assertTrue(link.contains("Prijavite se"));
    }

    @Test
    void sqlInjectionLoginForm() throws InterruptedException {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a")).click();
        driver.findElement(By.id("field-email")).sendKeys("test@test.com' OR '1'='1");
        Thread.sleep(500);
        driver.findElement(By.id("field-password")).sendKeys("anything");
        Thread.sleep(500);
        driver.findElement(By.id("submit-login")).click();
        Thread.sleep(2000);
        WebElement linkDiv = driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a"));
        String link = linkDiv.getText();
        assertFalse(link.contains("Odjava"));

    }

}
