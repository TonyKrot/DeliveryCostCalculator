import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicSeleniumTests {
    WebDriver driver;

    @BeforeEach
    void init() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    void CheckLamodaTitle() {
        driver.get("https://www.lamoda.ru/men-home/");
        assertEquals("Купить мужскую одежду и обувь в интернет магазине Lamoda.ru", driver.getTitle());
    }

    @Test
    void openMainMenu() throws InterruptedException {
        driver.get("https://www.lamoda.ru/men-home/");
        WebElement webFormButton = driver.findElement(By.xpath("//*[@id=\"vue-root\"]/div/header/div[2]/div[2]/nav/a[3]"));
        webFormButton.click();
        Thread.sleep(10000);
        WebElement menuButtonH1 = driver.findElement(By.xpath("//*[@id=\"vue-root\"]/div/header/div[3]/div/div/nav/a[5]"));
        assertEquals("Малышам", menuButtonH1.getText());
    }
}