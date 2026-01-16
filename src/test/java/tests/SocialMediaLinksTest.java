package tests;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SocialMediaLinksTest extends BaseTest {

    @Test
    void verifyFacebookLinkTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement footer = webDriver.findElement(By.id("footer"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", footer);
        Thread.sleep(500);

        String originalWindow = webDriver.getWindowHandle();

        WebElement facebookLink = webDriver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div/div[2]/div/a[1]"));
        facebookLink.click();
        Thread.sleep(1000);

        // Switch to new tab
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("facebook.com"), "Should open Facebook page");

        webDriver.close();
        webDriver.switchTo().window(originalWindow);
    }

    @Test
    void verifyInstagramLinkTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement footer = webDriver.findElement(By.id("footer"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", footer);
        Thread.sleep(500);

        String originalWindow = webDriver.getWindowHandle();

        WebElement instagramLink = webDriver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div/div[2]/div/a[2]"));
        instagramLink.click();
        Thread.sleep(1000);

        // Switch to new tab
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("instagram.com"), "Should open Instagram page");

        webDriver.close();
        webDriver.switchTo().window(originalWindow);
    }

    @Test
    void verifyYouTubeLinkTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement footer = webDriver.findElement(By.id("footer"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", footer);
        Thread.sleep(500);

        String originalWindow = webDriver.getWindowHandle();

        WebElement youtubeLink = webDriver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div/div[2]/div/a[3]"));
        youtubeLink.click();
        Thread.sleep(1000);

        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("youtube.com"), "Should open YouTube page");

        webDriver.close();
        webDriver.switchTo().window(originalWindow);
    }

    @Test
    void verifyLinkedInLinkTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement footer = webDriver.findElement(By.id("footer"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", footer);
        Thread.sleep(500);

        String originalWindow = webDriver.getWindowHandle();

        WebElement linkedinLink = webDriver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div/div[2]/div/a[4]"));
        linkedinLink.click();
        Thread.sleep(1000);

        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("linkedin.com"), "Should open LinkedIn page");

        webDriver.close();
        webDriver.switchTo().window(originalWindow);
    }
}


