import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class AppiumAppTests {
    private static final String APP = "https://github.com/appium-pro/TheApp/releases/download/v1.12.0/TheApp.apk";
    private static final String SERVER = "http://172.30.160.1:4723/";
    private AndroidDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        DesiredCapabilities ds = new DesiredCapabilities();
        ds.setCapability("platformName", "Android");
        ds.setCapability("platformVersion", "14");
        ds.setCapability("deviceName", "emulator-5554");
        ds.setCapability("app", APP);
        ds.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL(SERVER), ds);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void simpleTestWithImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        assertThat(driver.findElement(AppiumBy.accessibilityId("Login Screen")).isDisplayed()).isTrue();
    }

    @Test
    void simpleTestWithExplicitWait() {
        assertThat(new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Login Screen"))).isDisplayed()).isTrue();
    }

    @Test
    void simpleTestWithFluentWait() {
        WebElement login = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .until(driver -> driver.findElement(AppiumBy.accessibilityId("Login Screen")));
        assertThat(login.isDisplayed()).isTrue();
    }

    @Test
    void simpleActionsTest() {
        WebElement login = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Login Screen")));
        assertThat(login.getText()).isEqualTo("");
        assertThat(login.isDisplayed()).isTrue();
        assertThat(login.isSelected()).isFalse();
        assertThat(login.isEnabled()).isTrue();
    }

    @Test
    void testValidLoginCredentials() {
        String user = "alice";
        String password = "mypassword";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Login Screen")));
        login.click();
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("username")));
        username.sendKeys(user);
        driver.findElement(AppiumBy.accessibilityId("password")).sendKeys(password);
        driver.findElement(AppiumBy.accessibilityId("loginBtn")).click();

        WebElement loginMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[contains(@text, 'You are logged in')]")));
        assertThat(loginMessage.getText()).contains(user);
    }

    @Test
    void testInvalidUsername() {
        String user = "invalid_username";
        String password = "mypassword";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Login Screen")));
        login.click();
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("username")));
        username.sendKeys(user);
        driver.findElement(AppiumBy.accessibilityId("password")).sendKeys(password);
        driver.findElement(AppiumBy.accessibilityId("loginBtn")).click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android" +
                ".widget.TextView[contains(@text, 'Invalid login credentials, please try again')]")));
        assertThat(errorMessage.getText()).contains("Invalid username or password");
    }

    @Test
    void testInvalidPassword() {
        String user = "alice";
        String password = "invalid_password";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Login Screen")));
        login.click();
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("username")));
        username.sendKeys(user);
        driver.findElement(AppiumBy.accessibilityId("password")).sendKeys(password);
        driver.findElement(AppiumBy.accessibilityId("loginBtn")).click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Invalid login credentials, please try again')]")));
        assertThat(errorMessage.getText()).contains("Invalid username or password");
    }
}