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

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthenticationTest {

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
    public void Registration() throws Exception {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.linkText("Nemate račun? Kreirajte ga ovdje")).click();

        driver.findElement(By.xpath("//form[@id='customer-form']/div/div/div/label")).click();
        driver.findElement(By.id("field-firstname")).sendKeys("Haris");
        driver.findElement(By.id("field-lastname")).sendKeys("Susic");
        driver.findElement(By.id("field-email")).sendKeys("medobrundo@gmail.com");
        driver.findElement(By.id("field-password")).sendKeys("medinrodjendan123**");
        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.name("psgdpr")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a.logout.hidden-sm-down")
                )
        );

        assertTrue(
                logoutLink.getText().toLowerCase().contains("odjava"),
                "Očekivali smo da se nakon registracije pojavi link 'Odjava' (korisnik treba biti logovan)."
        );
    }


    @Test
    public void RegistrationWithExistingEmail() throws Exception { // DONE
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a")).click();
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.linkText("Nemate račun? Kreirajte ga ovdje")).click();

        driver.findElement(By.id("field-id_gender-1")).click();
        driver.findElement(By.id("field-firstname")).sendKeys("Tarik");
        driver.findElement(By.id("field-lastname")).sendKeys("Hodzic");
        driver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        driver.findElement(By.id("field-password")).sendKeys("NewPass123*");

        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.name("psgdpr")).click();

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("li.alert.alert-danger")
                )
        );

        String text = alert.getText().toLowerCase();
        assertTrue(
                text.contains("e-mail se već koristi") ||
                        text.contains("email se već koristi"),
                "Nije prikazana poruka da e-mail već postoji!"
        );
    }


    @Test
    public void RegistrationWithWrongEmail() throws Exception {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.linkText("Nemate račun? Kreirajte ga ovdje")).click();
        driver.findElement(By.xpath("//form[@id='customer-form']/div/div/div/label")).click();
        driver.findElement(By.id("field-firstname")).click();
        driver.findElement(By.id("field-firstname")).clear();
        driver.findElement(By.id("field-firstname")).sendKeys("Tarik");
        driver.findElement(By.id("field-lastname")).click();
        driver.findElement(By.id("field-lastname")).clear();
        driver.findElement(By.id("field-lastname")).sendKeys("Hodzic");
        driver.findElement(By.id("field-email")).click();
        driver.findElement(By.id("field-email")).clear();
        driver.findElement(By.id("field-email")).sendKeys("testmail@gmailcom");
        driver.findElement(By.id("field-password")).click();
        driver.findElement(By.id("field-password")).clear();
        driver.findElement(By.id("field-password")).sendKeys("NewPass123*");
        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.name("psgdpr")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("li.alert.alert-danger")
                )
        );
        String text = alert.getText().toLowerCase();
        assertTrue(
                text.contains("neispravan format"),
                "Nije prikazana poruka da je e-mail neispravan!"
        );
    }

    @Test
    public void RegistrationWithWeakPassword() throws Exception { // DONE
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.linkText("Nemate račun? Kreirajte ga ovdje")).click();

        driver.findElement(By.id("field-firstname")).sendKeys("Tarik");
        driver.findElement(By.id("field-lastname")).sendKeys("Hodzic");
        driver.findElement(By.id("field-email")).sendKeys("testingmail123@gmail.com");

        driver.findElement(By.id("field-password")).sendKeys("password123");

        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.name("psgdpr")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("li.alert.alert-danger")
                )
        );

        String text = alert.getText().toLowerCase();
        assertTrue(
                text.contains("minimalni rezultat") || text.contains("prosječno"),
                "Nije prikazana poruka da je lozinka preslaba!"
        );
    }


    @Test
    public void Login() throws Exception {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a")).click();

        driver.findElement(By.id("field-email")).sendKeys("haris.susic@stu.ibu.edu.ba");
        driver.findElement(By.id("field-password")).sendKeys("haris123*");
        driver.findElement(By.id("submit-login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logout = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a.logout.hidden-sm-down")
                )
        );

        assertTrue(
                logout.getText().toLowerCase().contains("odjava"),
                "Odjava se ne prikazuje — login vjerovatno nije uspio!"
        );
    }

    @Test
    public void LoginWithIncorrectPassword() throws Exception {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();

        driver.findElement(By.id("field-email")).sendKeys("hodzictarik99@gmail.com");
        driver.findElement(By.id("field-password")).sendKeys("wrongpass123*");
        driver.findElement(By.id("submit-login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("li.alert.alert-danger")
                )
        );

        String text = alert.getText().toLowerCase();

        assertTrue(
                text.contains("autentikacija nije uspela"),
                "Korisnik se loginovao sa pogrešnim podacima — očekivali smo error poruku!"
        );
    }


    @Test
    public void LoginWithNonExistingAccount() throws Exception {
        driver.get("https://imtec.ba/");
        driver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        driver.findElement(By.id("field-email")).click();
        driver.findElement(By.id("field-email")).clear();
        driver.findElement(By.id("field-email")).sendKeys("testing@gmail.com");
        driver.findElement(By.id("field-password")).click();
        driver.findElement(By.id("field-password")).clear();
        driver.findElement(By.id("field-password")).sendKeys("tester123*");
        driver.findElement(By.id("submit-login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("li.alert.alert-danger")
                )
        );

        String text = alert.getText().toLowerCase();

        assertTrue(
                text.contains("autentikacija nije uspela"),
                "Korisnik se loginovao sa nepostojećim podacima — očekivali smo error poruku!"
        );
    }


}
