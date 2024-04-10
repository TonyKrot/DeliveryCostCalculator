import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SeleniumShadowDomTests {

    @Test
    void openUrlTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
        WebElement subtitle = driver.findElement(By.cssSelector("h5"));
        assertEquals("Practice site", subtitle.getText());
        driver.close();
    }

    @Test
    void testShadowDom() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");

        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.cssSelector("p")));
        WebElement content = driver.findElement(By.id("content"));
        SearchContext shadowRoot = content.getShadowRoot();
        WebElement textElement = shadowRoot.findElement(By.cssSelector("p"));
        assertThat(textElement.getText()).contains("Hello Shadow DOM");
        driver.close();
    }
}
