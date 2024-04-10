import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SeleniumCookiesTests {

    @Test
    void openUrlTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
        WebElement subtitle = driver.findElement(By.cssSelector("h5"));
        assertEquals("Practice site", subtitle.getText());
        driver.close();
    }

    @Test
    void cookieTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        WebDriver.Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        assertThat(cookies).hasSize(2);
        Cookie username = options.getCookieNamed("username");
        assertThat(username.getValue()).isEqualTo("John Doe");
        assertThat(username.getPath()).isEqualTo("/");

        driver.findElement(By.id("refresh-cookies")).click();

        Cookie newCookie = new Cookie("new-name", "new-value");
        options.addCookie(newCookie);
        String readName = options.getCookieNamed(newCookie.getName()).getValue();
        assertThat(newCookie.getValue()).isEqualTo(readName);

        cookies = options.getCookies();
        assertThat(cookies).hasSize(3);

        driver.findElement(By.id("refresh-cookies")).click();

        options.deleteCookie(username);
        assertThat(options.getCookies()).hasSize(cookies.size() - 1);

        driver.findElement(By.id("refresh-cookies")).click();
        driver.close();
    }
}