package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import patterns.WebDriverFactory;

public abstract class BasePage {

    WebDriver driver;

    public BasePage(String browser) {
        driver = WebDriverFactory.createWebDriver(browser);
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void visit(String url) {
        driver.get(url);
    }

    public void click(WebElement element) {
        element.click();
    }

}