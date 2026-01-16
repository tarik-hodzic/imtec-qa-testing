package tests;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PersonalDataManagement extends BaseTest {

    @Test
    void accessGDPRPageTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement loginPageButton = webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span"));
        loginPageButton.click();
        Thread.sleep(500);

        WebElement emailInput = webDriver.findElement(By.xpath("//*[@id=\"field-email\"]"));
        emailInput.sendKeys("testemail@gmail.com");
        Thread.sleep(500);

        WebElement pwInput = webDriver.findElement(By.xpath("//*[@id=\"field-password\"]"));
        pwInput.sendKeys("testemail123!");
        Thread.sleep(500);

        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"submit-login\"]"));
        login.click();
        Thread.sleep(500);

        WebElement goToMyAccount = webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]/span"));
        goToMyAccount.click();
        Thread.sleep(500);

        WebElement gdprLink = webDriver.findElement(By.xpath("//*[@id=\"psgdpr-link\"]"));
        gdprLink.click();
        Thread.sleep(500);

        WebElement pageHeading = webDriver.findElement(By.cssSelector("h1"));
        assertTrue(pageHeading.getText().contains("GDPR - Lični podaci"), "GDPR page heading should be displayed");

        WebElement pdfButton = webDriver.findElement(By.xpath("//*[@id=\"exportDataToPdf\"]"));
        assertTrue(pdfButton.isDisplayed(), "PDF download button should be visible");

        WebElement csvButton = webDriver.findElement(By.xpath("//*[@id=\"exportDataToCsv\"]"));
        assertTrue(csvButton.isDisplayed(), "CSV download button should be visible"); }

    @Test
    void downloadPersonalDataAsPDFTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement loginPageButton = webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span"));
        loginPageButton.click();
        Thread.sleep(500);

        WebElement emailInput = webDriver.findElement(By.xpath("//*[@id=\"field-email\"]"));
        emailInput.sendKeys("testemail@gmail.com");
        Thread.sleep(500);

        WebElement pwInput = webDriver.findElement(By.xpath("//*[@id=\"field-password\"]"));
        pwInput.sendKeys("testemail123!");
        Thread.sleep(500);

        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"submit-login\"]"));
        login.click();
        Thread.sleep(500);

        WebElement profilePage = webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]"));
        profilePage.click();
        Thread.sleep(500);

        WebElement gdpr = webDriver.findElement(By.xpath("//*[@id=\"psgdpr-link\"]"));
        gdpr.click();
        Thread.sleep(500);

        WebElement pdfButton = webDriver.findElement(By.xpath("//*[@id=\"exportDataToPdf\"]"));
        String pdfHref = pdfButton.getAttribute("href");

        assertNotNull(pdfHref, "PDF button should have a download link");
        assertTrue(pdfHref.contains("Pdf") || pdfHref.contains("pdf"), "PDF link should be valid");

        pdfButton.click();
        Thread.sleep(1000);

        List<WebElement> errors = webDriver.findElements(By.cssSelector(".alert-danger, .error"));
        assertTrue(errors.isEmpty() || !errors.get(0).isDisplayed(), "No error should appear after clicking PDF download");
        Thread.sleep(2000);
    }


    @Test
    void downloadPersonalDataAsCSVTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement loginPageButton = webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span"));
        loginPageButton.click();
        Thread.sleep(500);

        WebElement emailInput = webDriver.findElement(By.xpath("//*[@id=\"field-email\"]"));
        emailInput.sendKeys("testemail@gmail.com");
        Thread.sleep(500);

        WebElement pwInput = webDriver.findElement(By.xpath("//*[@id=\"field-password\"]"));
        pwInput.sendKeys("testemail123!");
        Thread.sleep(500);

        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"submit-login\"]"));
        login.click();
        Thread.sleep(500);

        WebElement profilePage = webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]"));
        profilePage.click();
        Thread.sleep(500);

        WebElement gdpr = webDriver.findElement(By.xpath("//*[@id=\"psgdpr-link\"]"));
        gdpr.click();
        Thread.sleep(500);

        WebElement csvButton = webDriver.findElement(By.xpath("//*[@id=\"exportDataToCsv\"]"));
        String csvHref = csvButton.getAttribute("href");

        assertNotNull(csvHref, "CSV button should have a download link");
        assertTrue(csvHref.contains("Csv") || csvHref.contains("csv"), "CSV link should be valid");

        csvButton.click();
        Thread.sleep(1000);

        List<WebElement> errors = webDriver.findElements(By.cssSelector(".alert-danger, .error"));
        assertTrue(errors.isEmpty() || !errors.get(0).isDisplayed(), "No error should appear after clicking CSV download");
        Thread.sleep(2000);
    }

}
