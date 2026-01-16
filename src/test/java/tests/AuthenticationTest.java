package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthenticationTest extends BaseTest {

    private WebDriverWait getWait() {
        return new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    void registrationSuccessful() {
        webDriver.get(BASE_URL);

        webDriver.findElement(By.xpath("//*[@id='_desktop_user_info']/div/a/span")).click();
        webDriver.findElement(By.linkText("Nemate račun? Kreirajte ga ovdje")).click();

        webDriver.findElement(By.id("field-firstname")).sendKeys("Haris");
        webDriver.findElement(By.id("field-lastname")).sendKeys("Susic");
        webDriver.findElement(By.id("field-email")).sendKeys("test_" + System.currentTimeMillis() + "@gmail.com");
        webDriver.findElement(By.id("field-password")).sendKeys("Test12345**");

        webDriver.findElement(By.name("customer_privacy")).click();
        webDriver.findElement(By.name("psgdpr")).click();
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement logout =
                getWait().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a.logout.hidden-sm-down")
                ));

        assertTrue(logout.getText().toLowerCase().contains("odjava"),
                "Korisnik nije automatski prijavljen nakon registracije");
    }

    @Test
    void registrationWithExistingEmail() {
        webDriver.get(BASE_URL);

        webDriver.findElement(By.xpath("//*[@id='_desktop_user_info']/div/a/span")).click();
        webDriver.findElement(By.linkText("Nemate račun? Kreirajte ga ovdje")).click();

        webDriver.findElement(By.id("field-firstname")).sendKeys("Tarik");
        webDriver.findElement(By.id("field-lastname")).sendKeys("Hodzic");
        webDriver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        webDriver.findElement(By.id("field-password")).sendKeys("NewPass123*");

        webDriver.findElement(By.name("customer_privacy")).click();
        webDriver.findElement(By.name("psgdpr")).click();
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement alert =
                getWait().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("li.alert.alert-danger")
                ));

        assertTrue(alert.getText().toLowerCase().contains("e-mail"),
                "Nije prikazana poruka da email već postoji");
    }

    @Test
    void registrationWithInvalidEmail() {
        webDriver.get(BASE_URL);

        webDriver.findElement(By.xpath("//*[@id='_desktop_user_info']/div/a/span")).click();
        webDriver.findElement(By.linkText("Nemate račun? Kreirajte ga ovdje")).click();

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("field-firstname")));

        webDriver.findElement(By.id("field-firstname")).sendKeys("Tarik");
        webDriver.findElement(By.id("field-lastname")).sendKeys("Hodzic");
        webDriver.findElement(By.id("field-email")).sendKeys("testmail@gmailcom");
        webDriver.findElement(By.id("field-password")).sendKeys("NewPass123*");

        webDriver.findElement(By.name("customer_privacy")).click();
        webDriver.findElement(By.name("psgdpr")).click();
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement alert =
                getWait().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("li.alert.alert-danger")
                ));

        assertEquals(
                "Neispravan format.",
                alert.getText().trim(),
                "Nije prikazana tačna poruka za neispravan email format"
        );
    }



    @Test
    void loginSuccessful() {
        webDriver.get(BASE_URL);

        webDriver.findElement(By.xpath("//*[@id='_desktop_user_info']/div/a/span")).click();
        webDriver.findElement(By.id("field-email")).sendKeys("haris.susic@stu.ibu.edu.ba");
        webDriver.findElement(By.id("field-password")).sendKeys("haris123*");
        webDriver.findElement(By.id("submit-login")).click();

        WebElement logout =
                getWait().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a.logout.hidden-sm-down")
                ));

        assertTrue(logout.isDisplayed(), "Login nije uspio");
    }

    @Test
    void loginWithWrongPassword() {
        webDriver.get(BASE_URL);

        webDriver.findElement(By.xpath("//*[@id='_desktop_user_info']/div/a/span")).click();
        webDriver.findElement(By.id("field-email")).sendKeys("hodzictarik99@gmail.com");
        webDriver.findElement(By.id("field-password")).sendKeys("wrongpass123*");
        webDriver.findElement(By.id("submit-login")).click();

        WebElement alert =
                getWait().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("li.alert.alert-danger")
                ));

        assertTrue(alert.getText().toLowerCase().contains("autentikacija"),
                "Očekivana poruka za pogrešan login nije prikazana");
    }
}
