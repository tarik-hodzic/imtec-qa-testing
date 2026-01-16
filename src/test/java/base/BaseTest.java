package base;

import org.example.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected WebDriver webDriver;
    protected static final String BASE_URL = "https://imtec.ba/";
    protected JavascriptExecutor js = (JavascriptExecutor) webDriver;


    @BeforeEach
    void setup() {
        webDriver = WebDriverProvider.createDriver();
    }

    @AfterEach
    void tearDown() {
        if (webDriver != null) webDriver.quit();
    }
}
