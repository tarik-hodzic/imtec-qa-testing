package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SecurityTest extends BaseTest {

    @Test
    void httpsEnforcement() throws InterruptedException {
        webDriver.get("http://imtec.ba");
        Thread.sleep(1000);
        assertTrue(webDriver.getCurrentUrl().contains("https"));

        webDriver.get("http://imtec.ba/mojracun");
        Thread.sleep(1000);
        assertTrue(webDriver.getCurrentUrl().contains("https"));

        webDriver.get("http://imtec.ba/registracija");
        Thread.sleep(1000);
        assertTrue(webDriver.getCurrentUrl().contains("https"));

        webDriver.get("http://imtec.ba/prijava?back=https%3A%2F%2Fimtec.ba%2Fregistracija");
        Thread.sleep(1000);
        assertTrue(webDriver.getCurrentUrl().contains("https"));
    }

    @Test
    void protectedPage() throws InterruptedException {
        webDriver.get("https://imtec.ba/identitet");
        Thread.sleep(1000);

        WebElement headingDiv = webDriver.findElement(By.xpath("//*[@id=\"main\"]/header"));
        String heading = headingDiv.getText();

        assertEquals("Prijava u Vaš račun", heading);
    }

    @Test
    void logoutBackButton() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a")).click();

        webDriver.findElement(By.id("field-email"))
                .sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        Thread.sleep(1000);

        webDriver.findElement(By.id("field-password"))
                .sendKeys("Tarik123*");
        Thread.sleep(1000);

        webDriver.findElement(By.id("submit-login")).click();
        Thread.sleep(1000);

        webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]")).click();
        Thread.sleep(1000);

        webDriver.findElement(By.xpath("//*[@id=\"main\"]/footer/div/a")).click();
        Thread.sleep(1000);

        webDriver.navigate().back();
        Thread.sleep(1000);

        WebElement headingDiv = webDriver.findElement(By.xpath("//*[@id=\"main\"]/header"));
        String heading = headingDiv.getText();

        assertEquals("Prijava u Vaš račun", heading);
    }

    @Test
    void logoutAfterCookieDeletion() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a")).click();

        webDriver.findElement(By.id("field-email"))
                .sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        Thread.sleep(1000);

        webDriver.findElement(By.id("field-password"))
                .sendKeys("Tarik123*");
        Thread.sleep(1000);

        webDriver.findElement(By.id("submit-login")).click();
        Thread.sleep(1000);

        webDriver.manage().deleteAllCookies();
        Thread.sleep(500);

        webDriver.navigate().refresh();
        Thread.sleep(500);

        WebElement linkDiv =
                webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a"));

        assertTrue(linkDiv.getText().contains("Prijavite se"));
    }

    @Test
    void logoutShouldAffectAllTabs() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a")).click();

        webDriver.findElement(By.id("field-email"))
                .sendKeys("tarik.hodzic@stu.ibu.edu.ba");
        Thread.sleep(500);

        webDriver.findElement(By.id("field-password"))
                .sendKeys("Tarik123*");
        Thread.sleep(500);

        webDriver.findElement(By.id("submit-login")).click();
        Thread.sleep(1000);

        String tab1 = webDriver.getWindowHandle();
        ((JavascriptExecutor) webDriver).executeScript("window.open();");

        Set<String> allTabs = webDriver.getWindowHandles();
        String tab2 = "";

        for (String tab : allTabs) {
            if (!tab.equals(tab1)) {
                tab2 = tab;
            }
        }

        webDriver.switchTo().window(tab2);
        webDriver.get("https://imtec.ba/mojracun");
        Thread.sleep(2000);

        webDriver.switchTo().window(tab1);
        webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"main\"]/footer/div/a")).click();
        Thread.sleep(2000);

        webDriver.switchTo().window(tab2);
        webDriver.navigate().refresh();
        Thread.sleep(2000);

        WebElement linkDiv =
                webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a"));

        assertTrue(linkDiv.getText().contains("Prijavite se"));
    }

    @Test
    void sqlInjectionLoginForm() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.xpath("//div[@id='_desktop_user_info']/div/a")).click();

        webDriver.findElement(By.id("field-email"))
                .sendKeys("test@test.com' OR '1'='1");
        Thread.sleep(500);

        webDriver.findElement(By.id("field-password"))
                .sendKeys("anything");
        Thread.sleep(500);

        webDriver.findElement(By.id("submit-login")).click();
        Thread.sleep(2000);

        WebElement linkDiv =
                webDriver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a"));

        assertFalse(linkDiv.getText().contains("Odjava"));
    }
}
