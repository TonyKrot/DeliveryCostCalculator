import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.util.List;

public class SeleniumWebformTests {

    WebDriver driver;

    @BeforeEach
    void start() {
        driver = new ChromeDriver();
        driver.get(Constants.BASE_URL1);
    }

    @AfterEach
    void close() {
        driver.quit();
    }

    @Test
    void titleH1() {
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
    }

    @Test
    void h5PracticeSite() {
        WebElement h5PracticeSite = driver.findElement(By.cssSelector("h5"));
        assertEquals("Practice site", h5PracticeSite.getText());
    }

    @Test
    void imageFluid() {
        WebElement imageFluid = driver.findElement(By.className("img-fluid"));
        assertTrue(imageFluid.isDisplayed());
    }

    @Test
    void h1WebForm() {
        WebElement h1WebForm = driver.findElement(By.cssSelector("h1[class='display-6']"));
        assertEquals("Web form", h1WebForm.getText());
    }

    @Test
    void labelTextInput() {
        WebElement labelTextInput = driver.findElement(By.id("my-text-id"));
        assertTrue(labelTextInput.isDisplayed());
    }

    @Test
    void inputText() {
        WebElement inputMyText = driver.findElement(By.id("my-text-id"));
        inputMyText.sendKeys("test");
        assertEquals("test", inputMyText.getAttribute("value"));
    }

    @Test
    void labelPassword() {
        WebElement labelPassword = driver.findElement(By.xpath("//label[.//input[@type='password']]"));
        assertEquals("Password", labelPassword.getText());
    }

    @Test
    void inputPassword() {
        WebElement inputMyPassword = driver.findElement(By.name("my-password"));
        inputMyPassword.sendKeys("12345");
        assertEquals("12345", inputMyPassword.getAttribute("value"));
    }

    @Test
    void labelTextarea() {
        WebElement labelTextarea = driver.findElement(By.xpath("//label[.//textarea]"));
        assertEquals("Textarea", labelTextarea.getText());
    }

    @Test
    void textareaText() {
        WebElement textareaMy = driver.findElement(By.name("my-textarea"));
        textareaMy.sendKeys("SomeTextHere");
        assertEquals("SomeTextHere", textareaMy.getAttribute("value"));
    }

    @Test
    void labelDisabledInput() {
        WebElement labelDisabledInput = driver.findElement(By.xpath("//label[.//input[@name='my-disabled']]"));
        assertEquals("Disabled input", labelDisabledInput.getText());
        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
    }

    @Test
    void inputIsDisabled() {
        WebElement inputMyDisabled = driver.findElement(By.name("my-disabled"));
        assertFalse(inputMyDisabled.isEnabled());
        assertThrows(ElementNotInteractableException.class, () -> inputMyDisabled.sendKeys("test"));
        assertEquals("true", inputMyDisabled.getAttribute("disabled"));
        assertEquals("Disabled input", inputMyDisabled.getAttribute("placeholder"));
    }

    @Test
    void labelReadonlyInput() {
        WebElement labelReadonlyInput = driver.findElement(By.xpath("//label[.//input[@name='my-readonly']]"));
        Assertions.assertEquals("Readonly input", labelReadonlyInput.getText());
    }

    @Test
    void inputIsReadonly() {
        WebElement inputMyReadonly = driver.findElement(By.name("my-readonly"));
        assertEquals("true", inputMyReadonly.getAttribute("readonly"));
        assertEquals("Readonly input", inputMyReadonly.getAttribute("value"));
    }

    @Test
    void linkReturnIndex() {
        WebElement linkReturnIndex = driver.findElement(By.linkText("Return to index"));
        assertThat(linkReturnIndex.getTagName()).isEqualTo("a");
        assertThat(linkReturnIndex.getCssValue("cursor")).isEqualTo("pointer");
        linkReturnIndex.click();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/index.html", driver.getCurrentUrl());
    }

    @Test
    void spanCopyrightBoniGarc() {
        WebElement spanCopyrightBoniGarc = driver.findElement(By.className("text-muted"));
        assertEquals("Copyright © 2021-2023 Boni García", spanCopyrightBoniGarc.getText());
    }

    @Test
    void linkBoniGarcia() {
        WebElement linkBoniGarcia = driver.findElement(By.cssSelector("span a"));
        assertEquals("Boni García", linkBoniGarcia.getText());
        linkBoniGarcia.click();
        assertEquals("https://bonigarcia.dev/", driver.getCurrentUrl());
    }

    @Test
    public void testDropdownList() throws InterruptedException {
        WebElement dropdownSelectMenu = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdownSelectMenu);
        Thread.sleep(3000);
        select.selectByIndex(1);
        Thread.sleep(3000);
        select.selectByIndex(0);
        Thread.sleep(3000);
        select.selectByValue("2");
        Thread.sleep(3000);
        select.selectByVisibleText("Three");

        assertEquals("Three", select.getFirstSelectedOption().getText());
        assertTrue(select.getFirstSelectedOption().isSelected());

        //get all options
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            System.out.printf("Available Option: %s isSelected = %s%n", option.getText(), option.isSelected());
        }
        Thread.sleep(3000);
    }

    @Test
    public void testDataListDropdown() throws InterruptedException {
        WebElement inputElement = driver.findElement(By.name("my-datalist"));
        List<WebElement> options = driver.findElements(By.xpath("//datalist[@id='my-options']/option"));
        assertEquals(5, options.size()); //
        inputElement.sendKeys("San Francisco");
        Thread.sleep(3000);
        options = driver.findElements(By.xpath("//datalist[@id='my-options']/option"));
        options.getFirst();
        String selectedValue = inputElement.getAttribute("value");
        assertEquals("San Francisco", selectedValue);
    }

    @Test
    public void testFileUpload() throws InterruptedException {
        WebElement fileInput = driver.findElement(By.name("my-file"));
        fileInput.sendKeys("/Users/antonkrotkevic/test.js");
        Thread.sleep(3000);
        assertTrue(fileInput.getAttribute("value").contains("test.js"));
    }

    @Test
    public void testCheckedCheckbox() {
        WebElement checkBox = driver.findElement(By.id("my-check-1"));
        assertTrue(checkBox.isSelected());
    }

    @Test
    public void testDefaultCheckbox() {
        WebElement checkBoxDefault = driver.findElement(By.id("my-check-2"));
        checkBoxDefault.click();
        assertThat(checkBoxDefault.isSelected()).isTrue();
    }

    @Test
    public void testCheckedRadio() {
        WebElement checkBoxRadio = driver.findElement(By.id("my-radio-1"));
        assertTrue(checkBoxRadio.isSelected());
    }

    @Test
    public void testDefaultRadio() {
        WebElement BoxRadioDefault = driver.findElement(By.id("my-radio-2"));
        BoxRadioDefault.click();
        assertThat(BoxRadioDefault.isSelected()).isTrue();
    }

    @Test
    public void testSubmitButton() {
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        WebElement titleElement = driver.findElement(By.className("display-6"));
        assertEquals("Form submitted", titleElement.getText());
        WebElement receivedElement = driver.findElement(By.className("lead"));
        assertEquals("Received!", receivedElement.getText());
    }

    @Test
    void testColorPicker() throws InterruptedException {
        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        colorPicker.click();
        Thread.sleep(3000);
        assertTrue(colorPicker.isDisplayed());
        assertEquals("#563d7c", colorPicker.getAttribute("value"));
    }

    @Test
    public void testDatePickerLabel() {
        WebElement datePicker = driver.findElement(By.name("my-date"));
        assertTrue(datePicker.isDisplayed());
    }

    @Test
    void testDatePickerDay() throws InterruptedException {
        LocalDate today = LocalDate.now();
        WebElement datePicker = driver.findElement(By.name("my-date"));
        datePicker.click();
        Thread.sleep(3000);
        WebElement todayDayElement = driver.findElement(By.xpath(
                "/html/body/div/div[1]/table/tbody/tr[2]/td[3]"));
        todayDayElement.click();
        Thread.sleep(3000);
        assertThat(today).isToday();
    }

    @Test
    public void testExampleRangeElementVisibility() throws InterruptedException {
        WebElement range = driver.findElement(By.name("my-range"));
        assertEquals("5", range.getAttribute("value"));
        assertTrue(driver.findElement(By.name("my-range")).isDisplayed());
    }
}




