package tests;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WishlistFunctionalityTest extends BaseTest {

    @Test
    void viewWishlistPageTest() throws InterruptedException {
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

        WebElement wishlistPage = webDriver.findElement(By.xpath("//*[@id=\"wishlist-link\"]"));
        wishlistPage.click();
        Thread.sleep(500);

        WebElement pageHeading = webDriver.findElement(By.cssSelector("h1"));
        assertTrue(pageHeading.getText().contains("My wishlists"), "Wishlist page heading should be displayed");

        WebElement createNewListButton = webDriver.findElement(By.cssSelector("a.wishlist-add-to-new"));
        assertTrue(createNewListButton.isDisplayed(), "Create new list button should be visible");
    }


    @Test
    void createNewWishlistTest() throws InterruptedException {
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

        WebElement wishlistPage = webDriver.findElement(By.xpath("//*[@id=\"wishlist-link\"]"));
        wishlistPage.click();
        Thread.sleep(500);

        WebElement createNewList = webDriver.findElement(By.cssSelector("a.wishlist-add-to-new"));
        createNewList.click();
        Thread.sleep(1000);

        WebElement modal = webDriver.findElement(By.cssSelector(".modal, .wishlist-modal, [class*='modal']"));
        assertTrue(modal.isDisplayed(), "Modal should be visible");
    }

    @Test
    void addProductToWishlistTest() throws InterruptedException {
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

        WebElement product = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[2]/div/ul/li/a/img"));
        product.click();
        Thread.sleep(500);


        WebElement wishlistButton = webDriver.findElement(By.cssSelector("button.wishlist-button-add"));
        wishlistButton.click();
        Thread.sleep(1000);

        List<WebElement> modals = webDriver.findElements(By.cssSelector(".modal.show, .wishlist-modal, [class*='wishlist'][class*='modal']"));
        boolean modalVisible = modals.size() > 0 && modals.get(0).isDisplayed();

        assertTrue(modalVisible, "Wishlist modal or confirmation should appear");
    }
}
