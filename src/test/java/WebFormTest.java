import components.FooterComponent;
import components.HeaderComponent;
import extensions.AllureExtension;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import patterns.WebDriverFactory;
import PageObject.WebFormPage;
import io.qameta.allure.Feature;
import org.apache.commons.io.FileUtils;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steps.AllureSteps;
import steps.BaseSteps;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Extensions")
public class WebFormTest {
    WebDriver driver;
    WebFormPage webFormPage;
    HeaderComponent header;
    FooterComponent footer;

    @BeforeEach
    public void setup() {
        driver = WebDriverFactory.createWebDriver("chrome");
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        webFormPage = new WebFormPage(driver);
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testTextInput() {
        WebElement textInput = webFormPage.getTextInput();
        textInput.sendKeys("test");
        assertNotNull(textInput);
        assertEquals("test", textInput.getAttribute("value"));
        assertEquals("my-text-id", textInput.getAttribute("id"));
        assertEquals("myvalue", textInput.getAttribute("myprop"));
    }

    @Test
    public void testPasswordInput() {
        WebElement passwordInput = webFormPage.getPasswordInput();
        passwordInput.sendKeys("12345");
        assertEquals("12345", passwordInput.getAttribute("value"));
        assertNotNull(passwordInput);
        assertEquals("password", passwordInput.getAttribute("type"));
    }

    @Test
    public void testTextarea() {
        WebElement textarea = webFormPage.getTextarea();
        textarea.sendKeys("SomeTextHere");
        assertEquals("SomeTextHere", textarea.getAttribute("value"));
        assertNotNull(textarea);
        assertEquals("textarea", textarea.getTagName());
    }

    @Test
    public void testDisabledInput() {
        WebElement disabledInput = webFormPage.getDisabledInput();
        assertFalse(disabledInput.isEnabled());
        assertThrows(ElementNotInteractableException.class, () -> disabledInput.sendKeys("test"));
        assertEquals("Disabled input", disabledInput.getAttribute("placeholder"));
        assertNotNull(disabledInput);
        assertTrue(disabledInput.getAttribute("disabled").equals("true"));
    }

    @Test
    public void testReadonlyInput() {
        WebElement readonlyInput = webFormPage.getReadonlyInput();
        assertEquals("Readonly input", readonlyInput.getAttribute("value"));
        assertNotNull(readonlyInput);
        assertTrue(readonlyInput.getAttribute("readonly").equals("true"));
    }

    @Test
    public void testLinkReturnIndex () {
        WebElement linkReturnIndex = webFormPage.getLinkReturnIndex();
        assertThat(linkReturnIndex.getTagName()).isEqualTo("a");
        assertThat(linkReturnIndex.getCssValue("cursor")).isEqualTo("pointer");
        linkReturnIndex.click();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/index.html", driver.getCurrentUrl());
    }

    @Test
    public void testDropdownSelect() throws InterruptedException {
        WebElement dropdownSelectMenu = webFormPage.getDropdownSelect();
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
    }

    @Test
    public void testDropdownDatalist() throws InterruptedException {
        WebElement inputElement = webFormPage.getDropdownDatalist();
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
    public void testFileInput() throws InterruptedException {
        WebElement fileInput = webFormPage.getFileInput();
        fileInput.sendKeys("/Users/antonkrotkevic/test.js");
        Thread.sleep(3000);
        assertTrue(fileInput.getAttribute("value").contains("test.js"));
    }

    @Test
    public void testCheckedCheckbox() {
        WebElement checkedCheckbox = webFormPage.getCheckedCheckbox();
        assertNotNull(checkedCheckbox);
        assertTrue(checkedCheckbox.isSelected());
    }

    @Test
    public void testDefaultCheckbox() {
        WebElement defaultCheckbox = webFormPage.getDefaultCheckbox();
        assertNotNull(defaultCheckbox);
        assertFalse(defaultCheckbox.isSelected());
    }

    @Test
    public void testCheckedRadio() {
        WebElement checkedRadio = webFormPage.getCheckedRadio();
        assertNotNull(checkedRadio);
        assertTrue(checkedRadio.isSelected());
    }

    @Test
    public void testDefaultRadio() {
        WebElement defaultRadio = webFormPage.getDefaultRadio();
        assertNotNull(defaultRadio);
        assertFalse(defaultRadio.isSelected());
    }

    @Test
    public void testColorPicker() throws InterruptedException {
        WebElement colorPicker = webFormPage.getColorPicker();
        colorPicker.click();
        Thread.sleep(3000);
        assertTrue(colorPicker.isDisplayed());
        assertEquals("#563d7c", colorPicker.getAttribute("value"));
    }

    @Test
    public void testDatePicker() throws InterruptedException {
        LocalDate today = LocalDate.now();
        WebElement datePicker = webFormPage.getDatePicker();
        datePicker.click();
        Thread.sleep(3000);
        WebElement todayDayElement = driver.findElement(By.xpath(
                "/html/body/div/div[1]/table/tbody/tr[2]/td[3]"));
        todayDayElement.click();
        Thread.sleep(3000);
        assertThat(today).isToday();
    }

    @Test
    public void testRangeInput() {
        WebElement range = webFormPage.getRangeInput();
        assertEquals("5", range.getAttribute("value"));
        assertTrue(driver.findElement(By.name("my-range")).isDisplayed());
    }

    @Test
    public void testHiddenInput() {
        WebElement hiddenInput = webFormPage.getHiddenInput();
        assertNotNull(hiddenInput);
        assertEquals("hidden", hiddenInput.getAttribute("type"));
    }

    @Test
    public void testSubmitButton() {
        WebElement submitButton = webFormPage.getSubmitButton();
        submitButton.click();
        WebElement titleElement = driver.findElement(By.className("display-6"));
        assertEquals("Form submitted", titleElement.getText());
        WebElement receivedElement = driver.findElement(By.className("lead"));
        assertEquals("Received!", receivedElement.getText());
    }

    @Test
    public void testHeaderTitleIsDisplayed() {
        assertEquals(header.getTitleText(), "Hands-On Selenium WebDriver with Java");
    }

    @Test
    public void testHeaderSubTitleIsDisplayed() {
        assertEquals(header.getSubTitleText(), "Practice site");
    }

    @Test
    public void testHeaderLogoIsDisplayed() {
        header.clickLogo();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://github.com/bonigarcia/selenium-webdriver-java", currentUrl);
    }

    @Test
    public void testCopyrightText() {
        String expectedText = "Copyright © 2021-2023 Boni García";
        String actualText = footer.getCopyrightText();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void testBoniGarciaLink() {
        assertTrue(footer.isBoniGarciaLinkPresent());
    }
}