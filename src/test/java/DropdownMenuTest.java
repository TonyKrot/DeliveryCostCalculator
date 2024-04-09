import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DropdownMenuTest {

    WebDriver driver;
    @BeforeEach
    void start() {
        driver = new ChromeDriver();
    }
    @AfterEach
    void close() {
        driver.quit();
    }

    @Test
    public void testDropdownMenu() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        Thread.sleep(2000);

        WebElement dropdown1 = driver.findElement(By.id("my-dropdown-1"));
        new Actions(driver)
                .click(dropdown1)
                .perform();
        Thread.sleep(2000);
        assertTrue(dropdown1.isDisplayed());

        WebElement dropdown2 = driver.findElement(By.id("my-dropdown-2"));
        new Actions(driver)
                .contextClick(dropdown2)
                .perform();
        Thread.sleep(2000);
        assertTrue(dropdown2.isDisplayed());

        WebElement dropdown3 = driver.findElement(By.id("my-dropdown-3"));
        new Actions(driver)
                .doubleClick(dropdown3)
                .perform();
        Thread.sleep(2000);
        assertTrue(dropdown3.isDisplayed());
    }
}


