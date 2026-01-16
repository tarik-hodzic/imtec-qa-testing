package tests;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


import static org.junit.jupiter.api.Assertions.*;

class ProductDetailsPageTest extends BaseTest {

    @Test
    void checkIfProductImageIsDisplayed() throws  InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement randomProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[2]/div/ul/li/a/img"));
        randomProduct.click();
        Thread.sleep(1000);

        WebElement clickOnImage = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[1]/div"));
        clickOnImage.click();
        Thread.sleep(500);

        WebElement trueImage = webDriver.findElement(By.xpath("//*[@id=\"product-modal\"]/div/div/div/figure/picture/img"));
        assertTrue(trueImage.isDisplayed());
        Thread.sleep(1000);

    }

    @Test
    void priceIsVisibleAndItsNotNull() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement randomProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[2]/div/ul/li/a/img"));
        randomProduct.click();
        Thread.sleep(1000);

        WebElement priceElement = webDriver.findElement(By.cssSelector(".current-price-value"));
        assertTrue(priceElement.isDisplayed(), "Product price is not visible.");
        Thread.sleep(500);

        String priceValue = priceElement.getAttribute("content");
        assertNotNull(priceValue, "Price value is null.");
        Thread.sleep(500);

        double price = Double.parseDouble(priceValue);
        assertTrue(price > 0, "Product price is zero or invalid.");
        Thread.sleep(500);

    }

    @Test
    void addToCartButtonIsEnabledForAvailableProduct() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement randomProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        randomProduct.click();
        Thread.sleep(1000);

        WebElement addToCartButton = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));

        assertTrue(addToCartButton.isDisplayed(), "Add to Cart button is not visible.");
        assertTrue(addToCartButton.isEnabled(), "Add to Cart button is disabled for available products.");
    }

    @Test
    void addToCartButtonIsDisabledForUnavailableProducts() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement goToClimate = webDriver.findElement(By.xpath("//*[@id=\"category-327\"]/a"));
        goToClimate.click();
        Thread.sleep(1000);

        WebElement findProduct = webDriver.findElement(By.xpath("//*[@id=\"js-product-list\"]/div[1]/div[8]/article/div/div[1]/a/picture/img"));

        js.executeScript("arguments[0].scrollIntoView(true);", findProduct);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", findProduct);
        Thread.sleep(500);

        WebElement addToCartButton = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        assertFalse(addToCartButton.isEnabled(), "BUG: Add to cart is enabled for unavailable product, or product was unavailable at the time of first testing.");
        Thread.sleep(500);

        WebElement buyWithOneClick = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[3]/div[4]/div/button"));
        assertFalse(buyWithOneClick.isEnabled(), "BUG: Buy with One Click is enabled for unavailable product, or product was unavailable at the time of first testing.");
        Thread.sleep(500);

        WebElement buyWithInstallments = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[3]/div[6]/div/button"));
        assertFalse(buyWithInstallments.isEnabled(), "BUG: Buy with installments click is enabled for unavailable product, or product was unavailable at the time of first testing");
        Thread.sleep(500);
    }

    @Test
    void imageGalleryWorks() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement enterProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        enterProduct.click();
        Thread.sleep(500);

        WebElement mainImage = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/ul/li[1]/picture/img"));
        assertTrue(mainImage.isDisplayed(), "Main Image is not Visible.");

        WebElement secondThumbnail = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/ul/li[2]/picture/img"));
        assertTrue(secondThumbnail.isDisplayed(), "Second thumbnail is not visible");
        assertTrue(secondThumbnail.isEnabled(), "Second thumbnail is not clickable");

        secondThumbnail.click();
        Thread.sleep(500);
    }

}



