import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumLocatorsTests {

    WebDriver driver;


    @BeforeEach
    void start() {
        driver = new ChromeDriver();
        driver.get(Constants.BASE_URL1);
    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    void titleH1() {
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
    }

    @Test
    void h5PracticeSite() {
        WebElement h5PracticeSite = driver.findElement(By.cssSelector("h5"));
        Assertions.assertEquals("Practice site", h5PracticeSite.getText());
    }

    @Test
    void imageFluid() {
        WebElement imageFluid = driver.findElement(By.cssSelector("img[class='img-fluid']"));
        Assertions.assertTrue(imageFluid.isDisplayed());
    }

    @Test
    void h1WebForm() {
        WebElement h1WebForm = driver.findElement(By.cssSelector("h1[class='display-6']"));
        Assertions.assertEquals("Web form", h1WebForm.getText());
    }

    @Test
    void labelTextInput() {
        WebElement labelTextInput = driver.findElement(By.cssSelector("html > body > main > div > form > div > div:nth-of-type(1) > label:nth-of-type(1)"));
        Assertions.assertEquals("Text input", labelTextInput.getText());
    }

    @Test
    void inputMyText() {
        WebElement inputMyText = driver.findElement(By.cssSelector("input[id='my-text-id']"));
        inputMyText.sendKeys("test");
        Assertions.assertEquals("test", inputMyText.getAttribute("value"));
    }

    @Test
    void labelPassword() {
        WebElement labelPassword = driver.findElement(By.cssSelector("html > body > main > div > form > div > div:nth-of-type(1) > label:nth-of-type(2)"));
        Assertions.assertEquals("Password", labelPassword.getText());
    }

    @Test
    void inputMyPassword() {
        WebElement inputMyPassword = driver.findElement(By.cssSelector("input[name='my-password']"));
        inputMyPassword.sendKeys("12345");
        Assertions.assertEquals("12345", inputMyPassword.getAttribute("value"));
    }

    @Test
    void labelTextarea() {
        WebElement labelTextarea = driver.findElement(By.cssSelector("html > body > main > div > form > div > div:nth-of-type(1) > label:nth-of-type(3)"));
        Assertions.assertEquals("Textarea", labelTextarea.getText());
    }

    @Test
    void textareaMy() {
        WebElement textareaMy = driver.findElement(By.cssSelector("textarea[rows='3']"));
        textareaMy.sendKeys("SomeTextHere");
        Assertions.assertEquals("SomeTextHere", textareaMy.getAttribute("value"));
    }

    @Test
    void labelDisabledInput() {
        WebElement labelDisabledInput = driver.findElement(By.cssSelector("html > body > main > div > form > div > div:nth-of-type(1) > label:nth-of-type(4)"));
        Assertions.assertEquals("Disabled input", labelDisabledInput.getText());
    }
    
    @Test
    void inputMyDisabled() {
        WebElement inputMyDisabled = driver.findElement(By.cssSelector("input[placeholder='Disabled input']"));
        Assertions.assertTrue(inputMyDisabled.getAttribute("disabled").equals("true"));
        Assertions.assertTrue(inputMyDisabled.getAttribute("placeholder").equals("Disabled input"));
    }

    @Test
    void labelReadonlyInput() {
        WebElement labelReadonlyInput = driver.findElement(By.cssSelector("html > body > main > div > form > div > div:nth-of-type(1) > label:nth-of-type(5)"));
        Assertions.assertEquals("Readonly input", labelReadonlyInput.getText());
    }

    @Test
    void inputMyReadonly() {
        WebElement inputMyReadonly = driver.findElement(By.cssSelector("input[name='my-readonly']"));
        Assertions.assertTrue(inputMyReadonly.getAttribute("readonly").equals("true"));
        Assertions.assertTrue(inputMyReadonly.getAttribute("value").equals("Readonly input"));
    }

    @Test
    void linkReturnIndex() {
        WebElement linkReturnIndex = driver.findElement(By.cssSelector("div[class$='mt-3'] a"));
        Assertions.assertEquals("Return to index", linkReturnIndex.getText());
        linkReturnIndex.click();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/index.html", driver.getCurrentUrl());
    }

    @Test
    void spanCopyrightBoniGarc() {
        WebElement spanCopyrightBoniGarc = driver.findElement(By.cssSelector("span[class='text-muted']"));
        Assertions.assertEquals("Copyright © 2021-2023 Boni García", spanCopyrightBoniGarc.getText());
    }

    @Test
    void linkBoniGarc() {
        WebElement linkBoniGarc = driver.findElement(By.cssSelector("span a"));
        Assertions.assertEquals("Boni García", linkBoniGarc.getText());
        linkBoniGarc.click();
        Assertions.assertEquals("BONI GARCÍA", driver.findElement(By.cssSelector("#divSiteTitle")).getText());
    }

    @Test
    void labelDropdownSelect() throws InterruptedException {
        WebElement labelDropdownSelect = driver.findElement(By.xpath("//label[contains(@class, 'w-100')][.//select]"));
        Assertions.assertEquals("label", labelDropdownSelect.getTagName());
    }

    @Test
    void labelDropdownDatalist() {
        WebElement labelDropdownDatalist = driver.findElement(By.xpath("//label[@class='form-label w-100'][.//*[@id='my-options']]"));
        Assertions.assertEquals("Dropdown (datalist)", labelDropdownDatalist.getText());
    }

    @Test
    void labelDropdownDatalistCheck() {
        WebElement labelDropdownDatalistCheck = driver.findElement(By.name("my-datalist"));
        Assertions.assertTrue(labelDropdownDatalistCheck.getAttribute("placeholder").equals("Type to search..."));
    }

    @Test
    void labelFileInput() {
        WebElement labelFileInput = driver.findElement(By.xpath("//label[.//input[@type='file']]"));
        Assertions.assertEquals("File input", labelFileInput.getText());
    }

    @Test
    void checkedCheckbox() {
        WebElement checkedCheckbox = driver.findElement(By.xpath("//label[.//*[@id='my-check-1']]"));
        Assertions.assertEquals("Checked checkbox", checkedCheckbox.getText());
    }

    @Test
    void defaultCheckbox() {
        WebElement defaultCheckbox = driver.findElement(By.xpath("//label[.//*[@id='my-check-2']]"));
        Assertions.assertEquals("Default checkbox", defaultCheckbox.getText());
    }

    @Test
    void checkedRadio() {
        WebElement checkedRadio = driver.findElement(By.xpath("//label[.//*[@id='my-radio-1']]"));
        Assertions.assertEquals("Checked radio", checkedRadio.getText());
    }

    @Test
    void defaultRadio() {
        WebElement defaultRadio = driver.findElement(By.xpath("//label[.//*[@id='my-radio-2']]"));
        Assertions.assertEquals("Default radio", defaultRadio.getText());
    }

    @Test
    void submitButton() {
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Assertions.assertEquals("Submit", submitButton.getText());
        submitButton.click();
        Assertions.assertEquals("Form submitted", driver.findElement(By.cssSelector("h1[class='display-6']")).getText());
    }

    @Test
    void colorPicker() {
        WebElement colorPicker = driver.findElement(By.xpath("//label[.//input[@type='color']]"));
        Assertions.assertEquals("Color picker", colorPicker.getText());
    }

    @Test
    void myColorPicker() {
        WebElement myColorPicker = driver.findElement(By.cssSelector("input[name='my-colors']"));
        Assertions.assertEquals("input", myColorPicker.getTagName());
    }

    @Test
    void datePicker() throws InterruptedException {
        WebElement datePicker = driver.findElement(By.xpath("//label[.//input[@name='my-date']]"));
        Assertions.assertEquals("Date picker", datePicker.getText());
        datePicker.click();
        Thread.sleep(5000);
        WebElement datePickerDays = driver.findElement(By.cssSelector("div[class='datepicker-days']"));
        Assertions.assertTrue(datePickerDays.isDisplayed());
    }

    @Test
    void  exampleRange() {
        WebElement exampleRange = driver.findElement(By.xpath("//label[.//input[@min='0']]"));
        Assertions.assertEquals("Example range", exampleRange.getText());
        WebElement myRange = driver.findElement(By.cssSelector("input[name='my-range']"));
        Assertions.assertEquals("input", myRange.getTagName());
    }
}

