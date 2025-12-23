import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProfileSettingsTest {

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
    void editingPersonalData() throws InterruptedException {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.id("field-email")).click();
        driver.findElement(By.id("field-email")).clear();
        driver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        driver.findElement(By.id("field-password")).click();
        driver.findElement(By.id("field-password")).clear();
        driver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        driver.findElement(By.id("submit-login")).click();
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a[2]/span")).click();
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a[2]/span")).click();
        driver.findElement(By.xpath("//a[@id='identity-link']/span/i")).click();
        driver.findElement(By.id("field-firstname")).click();
        driver.findElement(By.id("field-firstname")).clear();
        driver.findElement(By.id("field-firstname")).sendKeys("Tarikk");
        driver.findElement(By.id("field-lastname")).click();
        driver.findElement(By.id("field-lastname")).clear();
        driver.findElement(By.id("field-lastname")).sendKeys("Hodzicc");
        driver.findElement(By.id("field-company")).click();
        driver.findElement(By.id("field-company")).clear();
        driver.findElement(By.id("field-company")).sendKeys("IBU");
        driver.findElement(By.id("field-password")).click();
        driver.findElement(By.id("field-password")).clear();
        driver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        driver.findElement(By.id("field-new_password")).click();
        driver.findElement(By.id("field-new_password")).clear();
        driver.findElement(By.id("field-new_password")).sendKeys("Tarik123*");
        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.name("psgdpr")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        WebElement alertDiv = driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article"));
        String alert = alertDiv.getText();
        assertEquals("Informacije uspješno nadograđene.", alert);
    }

    @Test
    void termsOfUseExistence() throws InterruptedException {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.id("field-email")).click();
        driver.findElement(By.id("field-email")).clear();
        driver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        driver.findElement(By.id("field-password")).click();
        driver.findElement(By.id("field-password")).clear();
        driver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        driver.findElement(By.id("submit-login")).click();
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a[2]/span")).click();
        driver.findElement(By.xpath("//a[@id='identity-link']/span/i")).click();
        driver.findElement(By.linkText("uslovima korištenja")).click();
        Thread.sleep(2000);
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
        WebElement headingDiv = driver.findElement(By.xpath("//*[@id='main']/header/h1"));
        String heading = headingDiv.getText();
        assertFalse(heading.contains("Stranica koju tražite nije pronađena"));
    }

    @Test
    void addingFirstAddress() throws InterruptedException {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.id("field-email")).click();
        driver.findElement(By.id("field-email")).clear();
        driver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        driver.findElement(By.id("field-password")).click();
        driver.findElement(By.id("field-password")).click();
        driver.findElement(By.id("field-password")).clear();
        driver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        driver.findElement(By.id("submit-login")).click();
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a[2]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"address-link\"]/span")).click();
        driver.findElement(By.id("field-company")).click();
        driver.findElement(By.id("field-company")).clear();
        driver.findElement(By.id("field-company")).sendKeys("IBU");
        driver.findElement(By.id("field-address1")).click();
        driver.findElement(By.id("field-address1")).clear();
        driver.findElement(By.id("field-address1")).sendKeys("Francuske revolucije bb");
        driver.findElement(By.id("field-postcode")).click();
        driver.findElement(By.id("field-postcode")).clear();
        driver.findElement(By.id("field-postcode")).sendKeys("71000");
        driver.findElement(By.id("field-city")).click();
        driver.findElement(By.id("field-city")).clear();
        driver.findElement(By.id("field-city")).sendKeys("Sarajevo");
        driver.findElement(By.id("field-phone")).click();
        driver.findElement(By.id("field-phone")).clear();
        driver.findElement(By.id("field-phone")).sendKeys("061234567");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        WebElement alertDiv = driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article"));
        String alert = alertDiv.getText();
        assertEquals("Adresa uspješno dodana.", alert);
    }

    @Test
    void editingAddress() throws InterruptedException {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.id("field-email")).click();
        driver.findElement(By.id("field-email")).clear();
        driver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        driver.findElement(By.id("field-password")).click();
        driver.findElement(By.id("field-password")).clear();
        driver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        driver.findElement(By.id("login-form")).submit();
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a[2]/span")).click();
        driver.findElement(By.xpath("//a[@id='addresses-link']/span/i")).click();
        driver.findElement(By.xpath("//article[@id='address-1829']/div[2]/a/span")).click();
        driver.findElement(By.id("field-address1")).click();
        driver.findElement(By.id("field-address1")).click();
        driver.findElement(By.id("field-address1")).clear();
        driver.findElement(By.id("field-address1")).sendKeys("Generala Izeta Nanica");
        driver.findElement(By.id("field-lastname")).click();
        driver.findElement(By.id("field-lastname")).clear();
        driver.findElement(By.id("field-lastname")).sendKeys("Hodzic");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        WebElement alertDiv = driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article"));
        String alert = alertDiv.getText();
        assertEquals("Adresa uspješno ažurirana.", alert);
    }

    @Test
    void addingAnotherAddress() throws InterruptedException {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.id("field-email")).click();
        driver.findElement(By.id("field-email")).clear();
        driver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        driver.findElement(By.id("field-password")).click();
        driver.findElement(By.id("field-password")).clear();
        driver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        driver.findElement(By.id("login-form")).submit();
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a[2]/span")).click();
        driver.findElement(By.xpath("//a[@id='addresses-link']/span/i")).click();
        driver.findElement(By.xpath("//section[@id='content']/div[3]/a/span")).click();
        driver.findElement(By.id("field-firstname")).click();
        driver.findElement(By.id("field-firstname")).clear();
        driver.findElement(By.id("field-firstname")).sendKeys("Tarikk");
        driver.findElement(By.id("field-address1")).click();
        driver.findElement(By.id("field-address1")).clear();
        driver.findElement(By.id("field-address1")).sendKeys("Zelenih beretki");
        driver.findElement(By.id("field-postcode")).click();
        driver.findElement(By.id("field-postcode")).clear();
        driver.findElement(By.id("field-postcode")).sendKeys("71000");
        driver.findElement(By.id("field-city")).click();
        driver.findElement(By.id("field-city")).clear();
        driver.findElement(By.id("field-city")).sendKeys("Sarajevo");
        driver.findElement(By.id("field-phone")).click();
        driver.findElement(By.id("field-phone")).clear();
        driver.findElement(By.id("field-phone")).sendKeys("062123456");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        WebElement alertDiv = driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article"));
        String alert = alertDiv.getText();
        assertEquals("Adresa uspješno dodana.", alert);
    }

    @Test
    void addressDeletion() throws InterruptedException {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.id("field-email")).click();
        driver.findElement(By.id("field-email")).clear();
        driver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        driver.findElement(By.id("field-password")).click();
        driver.findElement(By.id("field-password")).clear();
        driver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        driver.findElement(By.id("submit-login")).click();
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div")).click();
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a[2]/span")).click();
        driver.findElement(By.xpath("//a[@id='addresses-link']/span/i")).click();
        driver.findElement(By.xpath("//*[@id=\"address-1829\"]/div[2]/a[2]")).click();
        Thread.sleep(2000);
        WebElement alertDiv = driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article"));
        String alert = alertDiv.getText();
        assertEquals("Adresa uspješno obrisana.", alert);
    }
}