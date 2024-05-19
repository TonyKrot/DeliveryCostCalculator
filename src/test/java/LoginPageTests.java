import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObject.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

class LoginPageTests {
    WebDriver driver;
    LoginPage login;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        login = new LoginPage(driver);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testBasicLoginSuccess() {
        login.with("user", "user");
        assertThat(login.successBoxPresent()).isTrue();
    }

    @Test
    void testBasicLoginFailure() {
        login.with("test", "test");
        assertThat(login.invalidCredentialsBoxPresent()).isTrue();
    }
}
