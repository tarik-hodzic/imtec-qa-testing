package tests;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartManagementTest extends BaseTest {

    @Test
    void addSingleProductToCartTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement getRandomProductName = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[4]/div/ul/li/h5/a"));
        String productName = getRandomProductName.getText();
        Thread.sleep(200);

        WebElement randomProductPrice = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[4]/div/ul/li/div[1]/span[1]"));
        String productPrice = randomProductPrice.getText();
        String justPrice = productPrice.replace("KM", "").trim();

        WebElement openRandomProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[4]/div/ul/li/a/img"));
        openRandomProduct.click();
        Thread.sleep(500);

        WebElement addToCart = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCart.click();
        Thread.sleep(1500);

        WebElement finishShop = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        finishShop.click();
        Thread.sleep(500);

        WebElement randomProductInCartPrice = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[2]/span/strong"));
        String inCartPrice = randomProductInCartPrice.getText();
        String inCartJustPrice = inCartPrice.replace("KM", "").trim();

        WebElement inCartName = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[1]/a"));
        String inCartProductName = inCartName.getText();
        Thread.sleep(500);

        assertEquals(justPrice, inCartJustPrice);
        assertEquals(productName, inCartProductName);
        Thread.sleep(500);
    }

    @Test
    void addMultipleProductsToCartTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement firstProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        firstProduct.click();
        Thread.sleep(500);

        WebElement addToCartButton = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCartButton.click();
        Thread.sleep(1000);

        WebElement contShopping = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/button"));
        contShopping.click();

        WebElement goBack = webDriver.findElement(By.xpath("//*[@id=\"_desktop_logo\"]/a/img"));
        goBack.click();
        Thread.sleep(500);

        WebElement secondProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[4]/div/ul/li/a/img"));
        secondProduct.click();
        Thread.sleep(500);

        WebElement addToCartSecondButton = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCartSecondButton.click();
        Thread.sleep(1000);

        WebElement finalizeAdding = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        finalizeAdding.click();
        Thread.sleep(500);

        WebElement cartCount = webDriver.findElement(By.xpath("//*[@id='cart-subtotal-products']/span[1]"));
        String countText = cartCount.getText();
        int itemCount = Integer.parseInt(countText.split(" ")[0]);

        WebElement firstPrice = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li[1]/div/div[3]/div/div[2]/div/div[2]/span/strong"));
        String productPrice = firstPrice.getText();
        String justPrice = productPrice.replace("KM", "").trim();

        WebElement secondPrice = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li[2]/div/div[3]/div/div[2]/div/div[2]/span/strong"));
        String secondProductPrice = secondPrice.getText();
        String justPriceSecond = secondProductPrice.replace("KM", "").trim();

        WebElement totalValue = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div[2]/div[1]/span[2]"));
        String totalValueT = totalValue.getText();
        String onlyPriceTotalValue = totalValueT.replace("KM", "").trim();

        double price1 = Double.parseDouble(justPrice.replace(".", "").replace(",", "."));
        double price2 = Double.parseDouble(justPriceSecond.replace(".", "").replace(",", "."));
        double expectedTotal = price1 + price2;

        double actualTotal = Double.parseDouble(onlyPriceTotalValue.replace(".", "").replace(",", "."));

        assertEquals(2, itemCount, "Number of products in cart is not correct");
        assertEquals(expectedTotal, actualTotal, "Total price does not match sum of product prices");
    }

    @Test
    void updateProductQuantityInCartTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement product = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        product.click();
        Thread.sleep(500);
        WebElement addToCartButton = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCartButton.click();
        Thread.sleep(1000);

        WebElement goToCart = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        goToCart.click();
        Thread.sleep(500);

        WebElement priceElement = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li[1]/div/div[3]/div/div[2]/div/div[2]/span/strong"));
        String initialPriceText = priceElement.getText().replace("KM", "").trim();
        double initialPrice = Double.parseDouble(initialPriceText.replace(".", "").replace(",", "."));
        Thread.sleep(500);

        WebElement totalElement = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div[2]/div[1]/span[2]"));
        String initialTotalText = totalElement.getText().replace("KM", "").trim();
        double initialTotal = Double.parseDouble(initialTotalText.replace(".", "").replace(",", "."));
        Thread.sleep(500);

        WebElement increaseQty = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/span[3]/button[1]"));
        increaseQty.click();
        Thread.sleep(1000);

        WebElement newTotalElement = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div[2]/div[1]/span[2]"));
        String newTotalText = newTotalElement.getText().replace("KM", "").trim();
        double newTotal = Double.parseDouble(newTotalText.replace(".", "").replace(",", "."));
        Thread.sleep(500);

        WebElement qtyInput = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/input"));
        int quantity = Integer.parseInt(qtyInput.getAttribute("value"));
        Thread.sleep(500);

        assertEquals(2, quantity, "Quantity should be 2 after clicking increase");
        assertEquals(initialPrice * 2, newTotal, 0.01, "Total price should be double after increasing quantity");
    }

    @Test
    void removeProductFromCartTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement product = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        product.click();
        Thread.sleep(500);

        WebElement addToCartButton = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCartButton.click();
        Thread.sleep(1000);

        WebElement goToCart = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        goToCart.click();
        Thread.sleep(500);

        WebElement cartCount = webDriver.findElement(By.xpath("//*[@id='cart-subtotal-products']/span[1]"));
        String countText = cartCount.getText();
        int itemCount = Integer.parseInt(countText.split(" ")[0]);
        assertEquals(1, itemCount, "Cart should have 1 item before removal");

        WebElement removeButton = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[3]/div/a/i"));
        removeButton.click();
        Thread.sleep(1000);

        WebElement emptyCartMessage = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/span"));
        String emptyMessage = emptyCartMessage.getText();

        assertTrue(emptyMessage.contains("Nemate"), "Cart should be empty after removing product");
    }

    @Test
    void cartPersistenceAfterRefreshTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement product = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        product.click();
        Thread.sleep(500);
        WebElement addToCartButton = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCartButton.click();
        Thread.sleep(1000);
        WebElement goToCart = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        goToCart.click();
        Thread.sleep(500);

        WebElement productName = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[1]/a"));
        String nameBeforeRefresh = productName.getText();

        WebElement priceElement = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li[1]/div/div[3]/div/div[2]/div/div[2]/span/strong"));
        String priceBeforeRefresh = priceElement.getText();

        webDriver.navigate().refresh();
        Thread.sleep(1000);

        WebElement productNameAfter = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[1]/a"));
        String nameAfterRefresh = productNameAfter.getText();

        WebElement priceElementAfter = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li[1]/div/div[3]/div/div[2]/div/div[2]/span/strong"));
        String priceAfterRefresh = priceElementAfter.getText();

        WebElement cartCount = webDriver.findElement(By.xpath("//*[@id='cart-subtotal-products']/span[1]"));
        String countText = cartCount.getText();
        int itemCount = Integer.parseInt(countText.split(" ")[0]);

        assertEquals(1, itemCount, "Cart should still have 1 item after refresh");
        assertEquals(nameBeforeRefresh, nameAfterRefresh, "Product name should persist after refresh");
        assertEquals(priceBeforeRefresh, priceAfterRefresh, "Product price should persist after refresh");
    }

    @Test
    void cartBadgeUpdatesCorrectlyTest() throws InterruptedException {
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement cartBadge = webDriver.findElement(By.xpath("//*[@id=\"_desktop_cart\"]/div/div/span[2]"));
        String initialBadge = cartBadge.getText();
        String cleanBadge = initialBadge.replace("(", "").replace(")", "").trim();
        int initialCount = cleanBadge.isEmpty() ? 0 : Integer.parseInt(cleanBadge);
        assertEquals(0, initialCount, "Cart badge should be 0 initially");
        Thread.sleep(500);

        WebElement firstProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[2]/div/ul/li/a/img"));
        firstProduct.click();
        Thread.sleep(500);

        WebElement addToCartFirstProduct = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCartFirstProduct.click();
        Thread.sleep(1000);

        WebElement continueShopping = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/button"));
        continueShopping.click();
        Thread.sleep(500);

        WebElement cartBadgeAfterAdd = webDriver.findElement(By.xpath("//*[@id=\"_desktop_cart\"]/div/div/a/span[2]"));
        String oneProduct = cartBadgeAfterAdd.getText();
        String cleanBadgeAfterAdd = oneProduct.replace("(", "").replace(")", "").trim();
        int countAfterAdd = Integer.parseInt(cleanBadgeAfterAdd);
        assertEquals(1, countAfterAdd, "It should be one product");

        WebElement logoElement = webDriver.findElement(By.xpath("//*[@id=\"_desktop_logo\"]/a/img"));
        logoElement.click();
        Thread.sleep(500);

        WebElement secondProduct = webDriver.findElement(By.xpath("//*[@id=\"advanced_cms_wrapper_48\"]/div[2]/div[3]/div/ul/li/a/img"));
        secondProduct.click();
        Thread.sleep(500);

        WebElement addToCartSecondProduct = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCartSecondProduct.click();
        Thread.sleep(1200);

        WebElement continueShoppingAgain = webDriver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/button"));
        continueShoppingAgain.click();
        Thread.sleep(500);

        WebElement cartBadgeAfterAnotherAdd = webDriver.findElement(By.xpath("//*[@id=\"_desktop_cart\"]/div/div/a/span[2]"));
        String twoProducts = cartBadgeAfterAnotherAdd.getText();
        String cleanBadgeAfterAnotherAdd = twoProducts.replace("(", "").replace(")", "").trim();
        int countAfterAnotherAdd = Integer.parseInt(cleanBadgeAfterAnotherAdd);
        assertEquals(2, countAfterAnotherAdd, "It should be two products");

        WebElement goToCart = webDriver.findElement(By.xpath("//*[@id=\"_desktop_cart\"]/div/div/a/span[1]"));
        goToCart.click();
        Thread.sleep(500);

        WebElement removeItem = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li[2]/div/div[3]/div/div[3]/div/a/i"));
        removeItem.click();
        Thread.sleep(1000);

        WebElement cartBadgeAgainOneProduct = webDriver.findElement(By.xpath("//*[@id=\"_desktop_cart\"]/div/div/a/span[2]"));
        String oneProductAgain = cartBadgeAgainOneProduct.getText();
        String cleanBadgeAgainOneProduct = oneProductAgain.replace("(", "").replace(")","").trim();
        int countLastTime = Integer.parseInt(cleanBadgeAgainOneProduct);
        assertEquals(1, countLastTime, "It should be one again");
        Thread.sleep(500);
    }

}
