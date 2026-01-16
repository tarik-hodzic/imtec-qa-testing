package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

class ProfileSettingsTest extends BaseTest {

    @Test
    void editingPersonalData() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        webDriver.findElement(By.id("field-email")).clear();
        webDriver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        webDriver.findElement(By.id("field-password")).clear();
        webDriver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        webDriver.findElement(By.id("submit-login")).click();

        webDriver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a[2]/span")).click();
        webDriver.findElement(By.xpath("//a[@id='identity-link']/span/i")).click();

        webDriver.findElement(By.id("field-firstname")).clear();
        webDriver.findElement(By.id("field-firstname")).sendKeys("Tarikk");
        webDriver.findElement(By.id("field-lastname")).clear();
        webDriver.findElement(By.id("field-lastname")).sendKeys("Hodzicc");
        webDriver.findElement(By.id("field-company")).clear();
        webDriver.findElement(By.id("field-company")).sendKeys("IBU");

        webDriver.findElement(By.id("field-password")).clear();
        webDriver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        webDriver.findElement(By.id("field-new_password")).clear();
        webDriver.findElement(By.id("field-new_password")).sendKeys("Tarik123*");

        webDriver.findElement(By.name("customer_privacy")).click();
        webDriver.findElement(By.name("psgdpr")).click();
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(2000);
        WebElement alertDiv = webDriver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article"));
        assertEquals("Informacije uspješno nadograđene.", alertDiv.getText());
    }

    @Test
    void termsOfUseExistence() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a/span")).click();
        webDriver.findElement(By.id("field-email")).sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        webDriver.findElement(By.id("field-password")).sendKeys("Tarik123*");
        webDriver.findElement(By.id("submit-login")).click();

        webDriver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a[2]/span")).click();
        webDriver.findElement(By.xpath("//a[@id='identity-link']/span/i")).click();
        webDriver.findElement(By.linkText("uslovima korištenja")).click();

        Thread.sleep(2000);
        for (String tab : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(tab);
        }

        WebElement headingDiv = webDriver.findElement(By.xpath("//*[@id='main']/header/h1"));
        assertFalse(headingDiv.getText().contains("Stranica koju tražite nije pronađena"));
    }

    @Test
    void logoutFunctionality() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span"));
        loginButton.click();
        Thread.sleep(200);

        WebElement enterEmail = webDriver.findElement(By.xpath("//*[@id=\"field-email\"]"));
        enterEmail.sendKeys("testemail@gmail.com");

        WebElement enterPw = webDriver.findElement(By.xpath("//*[@id=\"field-password\"]"));
        enterPw.sendKeys("testemail123!");

        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"submit-login\"]"));
        login.click();
        Thread.sleep(500);

        WebElement goToMyProfile = webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]"));
        goToMyProfile.click();
        Thread.sleep(500);

        WebElement logout = webDriver.findElement(By.xpath("//*[@id=\"main\"]/footer/div/a"));
        logout.click();
        Thread.sleep(1000);

        String currentURL = webDriver.getCurrentUrl();


        assertEquals("https://imtec.ba/prijava?back=my-account", currentURL, "It should go back to login page");
        Thread.sleep(500);
    }

}
