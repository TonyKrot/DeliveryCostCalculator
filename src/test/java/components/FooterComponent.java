package components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FooterComponent {
    private final WebDriver driver;
    private final By copyrightTextBy = By.cssSelector("span.text-muted");
    private final By boniGarciaLinkBy = By.linkText("Boni García");

    public FooterComponent (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCopyrightText() {
        return driver.findElement(copyrightTextBy).getText();
    }

    public boolean isBoniGarciaLinkPresent() {
        return driver.findElement(boniGarciaLinkBy).isDisplayed();
    }
}
