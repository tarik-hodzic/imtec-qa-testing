package tests;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderHistoryTest extends BaseTest {

    @Test
    void accessOrderHistoryPageTest() throws InterruptedException {
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

        WebElement goToHistory = webDriver.findElement(By.xpath("//*[@id=\"history-link\"]/span"));
        goToHistory.click();
        Thread.sleep(500);

        WebElement orderHistoryPage = webDriver.findElement(By.xpath("//*[@id=\"main\"]/header/h1"));
        assertTrue(orderHistoryPage.isDisplayed(), "Order history page should be displayed");
        Thread.sleep(1000);

        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("istorijat"), "URL should contain 'istorijat'");
        Thread.sleep(500);
    }

    @Test
    void viewOrderHistoryListTest() throws InterruptedException {
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

        WebElement goToHistory = webDriver.findElement(By.xpath("//*[@id=\"history-link\"]/span"));
        goToHistory.click();
        Thread.sleep(500);

        WebElement orderTable = webDriver.findElement(By.cssSelector("table.table"));
        assertTrue(orderTable.isDisplayed(), "Order list table should be displayed");

        List<WebElement> orderRows = webDriver.findElements(By.cssSelector("table.table tbody tr"));
        assertFalse(orderRows.isEmpty(), "Order list should contain at least one order");

        WebElement referenceNumber = webDriver.findElement(By.cssSelector("table.table tbody tr th[scope='row']"));
        String refText = referenceNumber.getText();
        assertFalse(refText.isEmpty(), "Order reference number should be displayed");

        WebElement orderDate = webDriver.findElement(By.xpath("//table//tbody//tr//td[1]"));
        assertTrue(orderDate.isDisplayed(), "Order date should be displayed");
        assertFalse(orderDate.getText().isEmpty(), "Order date should not be empty");

        WebElement totalPrice = webDriver.findElement(By.cssSelector("td.text-xs-right"));
        assertTrue(totalPrice.isDisplayed(), "Total price should be displayed");
        assertTrue(totalPrice.getText().contains("KM"), "Total price should contain KM");

        WebElement orderStatus = webDriver.findElement(By.cssSelector("span.label-pill"));
        assertTrue(orderStatus.isDisplayed(), "Order status should be displayed");
        assertFalse(orderStatus.getText().isEmpty(), "Order status should not be empty");
        Thread.sleep(1000);
    }

    @Test
    void viewOrderDetailsTest() throws InterruptedException {
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

        WebElement goToHistory = webDriver.findElement(By.xpath("//*[@id=\"history-link\"]/span"));
        goToHistory.click();
        Thread.sleep(500);

        WebElement clickOnDetalji = webDriver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr/td[6]/a[1]"));
        clickOnDetalji.click();
        Thread.sleep(500);

        WebElement productName = webDriver.findElement(By.xpath("//*[@id=\"order-products\"]/tbody/tr/td[1]/strong/a"));
        assertTrue(productName.isDisplayed(), "Product name should be displayed");

        WebElement quantity = webDriver.findElement(By.xpath("//*[@id=\"order-products\"]/tbody/tr/td[2]"));
        assertTrue(quantity.isDisplayed(), "Product quantity should be displayed");

        WebElement unitPrice = webDriver.findElement(By.xpath("//*[@id=\"order-products\"]/tbody/tr/td[3]"));
        assertFalse(unitPrice.getText().isEmpty(), "Product price should be displayed");
        assertTrue(unitPrice.getText().contains("KM"), "Product price should contain KM");

        WebElement totalPrice = webDriver.findElement(By.xpath("//*[@id=\"order-products\"]/tfoot/tr[4]/td[2]"));
        assertTrue(totalPrice.isDisplayed(), "Total price should be displayed");
        assertTrue(totalPrice.getText().contains("KM"), "Total price should contain KM");
    }

    @Test
    void addMessageToOrderTest() throws InterruptedException {
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

        WebElement goToHistory = webDriver.findElement(By.xpath("//*[@id=\"history-link\"]/span"));
        goToHistory.click();
        Thread.sleep(500);

        WebElement clickOnDetalji = webDriver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr/td[6]/a[1]"));
        clickOnDetalji.click();
        Thread.sleep(500);

        Select selectProduct = new Select(webDriver.findElement(By.xpath("//*[@id=\"content\"]/section[2]/form/section/div[1]/div/select")));
        selectProduct.selectByValue("24108");
        Thread.sleep(200);

        WebElement textArea = webDriver.findElement(By.xpath("//*[@id=\"content\"]/section[2]/form/section/div[2]/div/textarea"));
        textArea.sendKeys("Although this is just for testing purposes, I think this TV is amazing!");
        Thread.sleep(200);

        WebElement send = webDriver.findElement(By.xpath("//*[@id=\"content\"]/section[2]/form/footer/button"));
        send.click();
        Thread.sleep(500);

        WebElement successMessage = webDriver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li"));
        assertEquals("Poruka uspješno poslana", successMessage.getText());
        Thread.sleep(500);
    }


}
