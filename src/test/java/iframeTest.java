import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class iframeTest {

    @Test
    void openUrlTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
        WebElement subtitle = driver.findElement(By.cssSelector("h5"));
        assertEquals("Practice site", subtitle.getText());
        driver.close();
    }

    @Test
    void iframeTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");

        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("lead")));
        WebElement iframeElement = driver.findElement(By.id("my-iframe"));
        driver.switchTo().frame(iframeElement);
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("display-6")));
        assertThat(driver.findElement(By.className("lead")).getText()).contains("Lorem ipsum dolor sit amet");
        driver.switchTo().defaultContent();
        assertThat(driver.findElement(By.className("display-6")).getText()).contains("IFrame");
        driver.close();
    }
}
