package PageObject;

import components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private final WebDriver driver;
    private final HeaderComponent headerComponent;

    @FindBy(xpath = "//a[contains(text(), 'Web form')]")
    private WebElement webFormLink;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        headerComponent = new HeaderComponent(driver);
    }

    public void clickWebFormLink() {
        webFormLink.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }
}
