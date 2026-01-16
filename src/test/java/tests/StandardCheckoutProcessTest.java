package tests;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

class StandardCheckoutProcessTest extends BaseTest{

    @Test
    void successfulCheckoutWithValidData() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement randomProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[2]/div/ul/li/a/img"));
        randomProduct.click();
        Thread.sleep(1000);

        WebElement addToCart = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCart.click();
        Thread.sleep(2000);

        WebElement popupFinishShopping = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        popupFinishShopping.click();
        Thread.sleep(1000);

        String currentUrl = webDriver.getCurrentUrl();
        assertEquals("https://imtec.ba/korpa?action=show", currentUrl);
        Thread.sleep(500);

        WebElement finishShopping = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a"));
        finishShopping.click();
        Thread.sleep(500);

        WebElement inputName = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_firstname\"]"));
        inputName.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputSurname = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_lastname\"]"));
        inputSurname.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputEmail = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_email\"]"));
        inputEmail.sendKeys("test.email@gmail.com");
        Thread.sleep(500);

        WebElement checkBox = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_psgdpr_check\"]"));
        checkBox.click();
        Thread.sleep(500);

        WebElement inputNameSecond = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_firstname\"]"));
        inputNameSecond.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputSurnameSecond = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_lastname\"]"));
        inputSurnameSecond.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputAddress = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_address1\"]"));
        inputAddress.sendKeys("Test Address 77208");
        Thread.sleep(500);

        WebElement inputCity = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_city\"]"));
        inputCity.sendKeys("Tuzla Test");
        Thread.sleep(500);

        WebElement inputPhone = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_phone\"]"));
        inputPhone.sendKeys("062865645");
        Thread.sleep(500);

        WebElement finishOrder = webDriver.findElement(By.xpath("//*[@id=\"ets_onepagecheckout\"]/div[4]/div[2]/div/button"));
        finishOrder.click();
        Thread.sleep(2500);


    }

    @Test
    void submitCheckoutWithoutCheckingTerms() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement randomProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        randomProduct.click();
        Thread.sleep(1000);

        WebElement addToCart = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCart.click();
        Thread.sleep(2000);

        WebElement popupFinishShopping = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        popupFinishShopping.click();
        Thread.sleep(1000);

        String currentUrl = webDriver.getCurrentUrl();
        assertEquals("https://imtec.ba/korpa?action=show", currentUrl);
        Thread.sleep(500);

        WebElement finishShopping = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a"));
        finishShopping.click();
        Thread.sleep(500);

        WebElement inputName = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_firstname\"]"));
        inputName.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputSurname = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_lastname\"]"));
        inputSurname.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputEmail = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_email\"]"));
        inputEmail.sendKeys("test.email@gmail.com");
        Thread.sleep(500);


        WebElement inputNameSecond = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_firstname\"]"));
        inputNameSecond.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputSurnameSecond = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_lastname\"]"));
        inputSurnameSecond.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputAddress = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_address1\"]"));
        inputAddress.sendKeys("Test Address 77208");
        Thread.sleep(500);

        WebElement inputCity = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_city\"]"));
        inputCity.sendKeys("Tuzla Test");
        Thread.sleep(500);

        WebElement inputPhone = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_phone\"]"));
        inputPhone.sendKeys("062865645");
        Thread.sleep(500);

        WebElement uncheckDefault = webDriver.findElement(By.xpath("//*[@id=\"conditions_to_approve\"]"));
        uncheckDefault.click();
        Thread.sleep(1000);

        WebElement finishOrder = webDriver.findElement(By.xpath("//*[@id=\"ets_onepagecheckout\"]/div[4]/div[2]/div/button"));
        finishOrder.click();
        Thread.sleep(2500);

        WebElement assertErrorMessage = webDriver.findElement(By.xpath("//*[@id=\"onepagecheckout-information-errros\"]/div/div/ul/li"));
        assertEquals("You must accept our terms of service in order to complete your order.", assertErrorMessage.getText());
        Thread.sleep(500);

    }

    @Test
    void submitCheckoutWithInvalidEmailFormat() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement randomProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        randomProduct.click();
        Thread.sleep(1000);

        WebElement addToCart = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCart.click();
        Thread.sleep(2000);

        WebElement popupFinishShopping = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        popupFinishShopping.click();
        Thread.sleep(1000);

        String currentUrl = webDriver.getCurrentUrl();
        assertEquals("https://imtec.ba/korpa?action=show", currentUrl);
        Thread.sleep(500);

        WebElement finishShopping = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a"));
        finishShopping.click();
        Thread.sleep(500);

        WebElement inputName = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_firstname\"]"));
        inputName.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputSurname = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_lastname\"]"));
        inputSurname.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputEmail = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_email\"]"));
        inputEmail.sendKeys("test.email@gmail.com");
        Thread.sleep(500);

        WebElement inputNameSecond = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_firstname\"]"));
        inputNameSecond.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputSurnameSecond = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_lastname\"]"));
        inputSurnameSecond.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputAddress = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_address1\"]"));
        inputAddress.sendKeys("Test Address 77208");
        Thread.sleep(500);

        WebElement inputCity = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_city\"]"));
        inputCity.sendKeys("Tuzla Test");
        Thread.sleep(500);

        WebElement inputPhone = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_phone\"]"));
        inputPhone.sendKeys("062865645");
        Thread.sleep(500);

        WebElement finishOrder = webDriver.findElement(By.xpath("//*[@id=\"ets_onepagecheckout\"]/div[4]/div[2]/div/button"));
        finishOrder.click();
        Thread.sleep(2500);

        WebElement getErrorMessage = webDriver.findElement(By.xpath("//*[@id=\"onepagecheckout-information-errros\"]/div/div"));
        assertEquals("×\n" + "Please fill in all required fields with valid information", getErrorMessage.getText());
        Thread.sleep(500);

        WebElement getInvalidEmailText = webDriver.findElement(By.xpath("//*[@id=\"customer-login\"]/div[5]/div/span"));
        assertEquals("Email is invalid", getInvalidEmailText.getText());
        Thread.sleep(500);



    }

    @Test
    void submitCheckoutWithExcessiveProductQuantity() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement randomProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        randomProduct.click();
        Thread.sleep(1000);

        WebElement amountInput = webDriver.findElement(By.xpath("//*[@id=\"quantity_wanted\"]"));
        amountInput.sendKeys("999");
        Thread.sleep(500);

        WebElement addToCart = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCart.click();
        Thread.sleep(1000);

        WebElement cartCount = webDriver.findElement(By.cssSelector(".cart-products-count"));
        String countText = cartCount.getText();

        assertTrue(countText.equals("(0)") || countText.equals("0"),
                "Product should not be added to cart when excessive quantity is entered");
    }

    @Test
    void submitCheckoutWithoutAnyItems() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement randomProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        randomProduct.click();
        Thread.sleep(1000);

        WebElement addToCart = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCart.click();
        Thread.sleep(2000);

        WebElement popupFinishShopping = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        popupFinishShopping.click();
        Thread.sleep(1000);

        WebElement deleteProduct = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[3]/div/a/i"));
        deleteProduct.click();
        Thread.sleep(1000);

        WebElement finishButton = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/button"));
        assertFalse(finishButton.isEnabled(), "It should not be enabled.");
        Thread.sleep(500);

        WebElement errorMessage = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/span"));
        assertEquals("Nemate više artikala u korpi", errorMessage.getText());
        Thread.sleep(500);

        WebElement continueShopping = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/a"));
        assertTrue(continueShopping.getText().contains("Nastavi kupovinu"));
        continueShopping.click();
        Thread.sleep(1000);

        String currentURL = webDriver.getCurrentUrl();
        assertEquals("https://imtec.ba/", currentURL); }

    @Test
    void submitCheckoutWithVeryLongInput() throws  InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement randomProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        randomProduct.click();
        Thread.sleep(1000);

        WebElement addToCart = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCart.click();
        Thread.sleep(2000);

        WebElement popupFinishShopping = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        popupFinishShopping.click();
        Thread.sleep(1000);

        WebElement finishShopping = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a"));
        finishShopping.click();
        Thread.sleep(500);

        WebElement inputName = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_firstname\"]"));
        inputName.sendKeys("Testtttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");

        Thread.sleep(500);

        WebElement inputSurname = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_lastname\"]"));
        inputSurname.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputEmail = webDriver.findElement(By.xpath("//*[@id=\"customer_guest_email\"]"));
        inputEmail.sendKeys("test.email@gmail.com");
        Thread.sleep(500);

        WebElement inputNameSecond = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_firstname\"]"));
        inputNameSecond.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputSurnameSecond = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_lastname\"]"));
        inputSurnameSecond.sendKeys("Test");
        Thread.sleep(500);

        WebElement inputAddress = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_address1\"]"));
        inputAddress.sendKeys("Test Address 77208");
        Thread.sleep(500);

        WebElement inputCity = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_city\"]"));
        inputCity.sendKeys("Tuzla Test");
        Thread.sleep(500);

        WebElement inputPhone = webDriver.findElement(By.xpath("//*[@id=\"shipping_address_phone\"]"));
        inputPhone.sendKeys("062865645");
        Thread.sleep(500);

        WebElement finishOrder = webDriver.findElement(By.xpath("//*[@id=\"ets_onepagecheckout\"]/div[4]/div[2]/div/button"));
        finishOrder.click();
        Thread.sleep(4500);

        String getCurrentURL = webDriver.getCurrentUrl();
        assertTrue(getCurrentURL.contains("checkout"), "BUG: Order went through but it should not! ");
        Thread.sleep(1000);

    }
}
