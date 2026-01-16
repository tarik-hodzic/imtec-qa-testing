package tests;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AddressManagementTest extends BaseTest {

    @Test
    void viewAddressesPageTest() throws InterruptedException {
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
        Thread.sleep(1000);

        WebElement addresses = webDriver.findElement(By.xpath("//*[@id=\"addresses-link\"]"));
        addresses.click();
        Thread.sleep(500);

        WebElement pageHeading = webDriver.findElement(By.cssSelector("h1"));
        assertTrue(pageHeading.getText().contains("adrese") || pageHeading.getText().contains("Adrese"), "Addresses page heading should be displayed");

        List<WebElement> addressesList = webDriver.findElements(By.cssSelector(".address"));
        assertFalse(addressesList.isEmpty(), "At least one address should be displayed");

        WebElement addNewAddressLink = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/a"));
        assertTrue(addNewAddressLink.isDisplayed(), "Add new address link should be visible");
    }

    @Test
    void addNewAddressTest() throws InterruptedException {
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

        WebElement addresses = webDriver.findElement(By.xpath("//*[@id=\"addresses-link\"]"));
        addresses.click();
        Thread.sleep(500);

        WebElement addNewAddressLink = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/a"));
        addNewAddressLink.click();
        Thread.sleep(500);

        WebElement aliasInput = webDriver.findElement(By.xpath("//*[@id=\"field-alias\"]"));
        aliasInput.clear();
        aliasInput.sendKeys("Nova Adresa Test");

        WebElement firstNameInput = webDriver.findElement(By.xpath("//*[@id=\"field-firstname\"]"));
        firstNameInput.clear();
        firstNameInput.sendKeys("New Test");

        WebElement lastNameInput = webDriver.findElement(By.xpath("//*[@id=\"field-lastname\"]"));
        lastNameInput.clear();
        lastNameInput.sendKeys("New Test");

        WebElement addressInput = webDriver.findElement(By.xpath("//*[@id=\"field-address1\"]"));
        addressInput.clear();
        addressInput.sendKeys("Testna ulica 123");

        WebElement cityInput = webDriver.findElement(By.xpath("//*[@id=\"field-city\"]"));
        cityInput.clear();
        cityInput.sendKeys("Sarajevo");

        WebElement postalCodeInput = webDriver.findElement(By.xpath("//*[@id=\"field-postcode\"]"));
        postalCodeInput.clear();
        postalCodeInput.sendKeys("71000");

        WebElement phoneInput = webDriver.findElement(By.xpath("//*[@id=\"field-phone\"]"));
        phoneInput.clear();
        phoneInput.sendKeys("061123456");
        Thread.sleep(500);

        WebElement saveButton = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/footer/button"));
        saveButton.click();
        Thread.sleep(1000);

        WebElement successMessage = webDriver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li"));
        assertEquals("Adresa uspješno dodana.", successMessage.getText());

        WebElement pageSource = webDriver.findElement(By.tagName("body"));
        assertTrue(pageSource.getText().contains("Test Address") || pageSource.getText().contains("Testna ulica"), "New address should be displayed");
    }

    @Test
    void editExistingAddressTest() throws InterruptedException {
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

        WebElement addresses = webDriver.findElement(By.xpath("//*[@id=\"addresses-link\"]"));
        addresses.click();
        Thread.sleep(500);

        WebElement editAddressLink = webDriver.findElement(By.xpath("//a[contains(@data-link-action,'edit-address')]"));
        editAddressLink.click();

        WebElement aliasInput = webDriver.findElement(By.xpath("//*[@id=\"field-alias\"]"));
        aliasInput.clear();
        aliasInput.sendKeys("Editovana Adresa");

        WebElement addressInput = webDriver.findElement(By.xpath("//*[@id=\"field-address1\"]"));
        addressInput.clear();
        addressInput.sendKeys("Editovana ulica 456");
        Thread.sleep(500);

        WebElement saveButton = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/footer/button"));
        saveButton.click();
        Thread.sleep(1000);

        WebElement successMessage = webDriver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li"));
        assertEquals("Adresa uspješno ažurirana.", successMessage.getText());

        WebElement pageSource = webDriver.findElement(By.tagName("body"));
        assertTrue(pageSource.getText().contains("Editovana Adresa") || pageSource.getText().contains("Editovana ulica"), "Updated address should be displayed");
    }

    @Test
    void deleteAddressTest() throws InterruptedException {
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

        WebElement addresses = webDriver.findElement(By.xpath("//*[@id=\"addresses-link\"]"));
        addresses.click();
        Thread.sleep(500);

        List<WebElement> addressesBefore = webDriver.findElements(By.xpath("//article[contains(@class,'address')]"));
        int countBefore = addressesBefore.size();

        WebElement deleteAddressLink = webDriver.findElement(By.xpath("//a[contains(@data-link-action,'delete-address')]"));
        deleteAddressLink.click();
        Thread.sleep(1000);

        WebElement successMessage = webDriver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li"));
        assertEquals("Adresa uspješno obrisana.", successMessage.getText());

        List<WebElement> addressesAfter = webDriver.findElements(By.xpath("//article[contains(@class,'address')]"));
        int countAfter = addressesAfter.size();

        assertEquals(countBefore - 1, countAfter, "Address count should decrease by 1 after deletion");
    }


}
