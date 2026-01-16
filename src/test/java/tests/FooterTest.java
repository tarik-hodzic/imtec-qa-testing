package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

class FooterTest extends BaseTest {

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
            webDriver.get(page);
            Thread.sleep(1500);
            WebElement footer = webDriver.findElement(By.tagName("footer"));
            assertTrue(footer.isDisplayed(), "Footer is not visible on page: " + page);
        }
    }

    @Test
    void footerCompanyInformation() throws InterruptedException {
        webDriver.get(BASE_URL);
        Thread.sleep(1500);

        WebElement footer = webDriver.findElement(By.id("contact-infos"));
        String footerText = footer.getText().toLowerCase();

        assertTrue(footerText.contains("ilijaš"));
        assertTrue(footerText.contains("bosna"));
        assertTrue(footerText.contains("@"));
        assertTrue(footerText.contains("+387"));
    }

    @Test
    void footerSocialMediaLinksExistence() {
        webDriver.get(BASE_URL);

        assertNotNull(webDriver.findElement(By.cssSelector("a[aria-label='Facebook']")).getAttribute("href"));
        assertNotNull(webDriver.findElement(By.cssSelector("a[aria-label='Instagram']")).getAttribute("href"));
        assertNotNull(webDriver.findElement(By.cssSelector("a[aria-label='Linkedin']")).getAttribute("href"));
        assertNotNull(webDriver.findElement(By.cssSelector("a[aria-label='Youtube']")).getAttribute("href"));
    }

    @Test
    void footerLinks() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.id("link-cms-page-7-1")).click();
        Thread.sleep(1500);

        WebElement headingDiv =
                webDriver.findElement(By.xpath("//*[@id=\"main\"]/header/h1"));

        String heading = headingDiv.getText();
        assertTrue(heading.contains("O nama"));
    }
}
