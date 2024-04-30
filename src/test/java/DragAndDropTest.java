import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DragAndDropTest {
    @Test
    public void testDragAndDrop() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");

        Actions actions = new Actions(driver);

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("target"));

        actions.dragAndDrop(source, target).build().perform();
        WebElement elementToCheck = driver.findElement(By.id("draggable"));

        assertEquals(612, elementToCheck.getLocation().getX());
        assertEquals(303, elementToCheck.getLocation().getY());

        driver.close();

    }
}
