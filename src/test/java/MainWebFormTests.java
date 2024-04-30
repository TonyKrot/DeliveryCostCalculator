import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import PageObject.MainPage;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(SeleniumJupiter.class)
public class MainWebFormTests {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setup() {
        this.driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSwitchToWebFormPage() {
        mainPage.getHeaderComponent().getTitleText();
        mainPage.clickWebFormLink();
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", mainPage.getTitle());
    }
}