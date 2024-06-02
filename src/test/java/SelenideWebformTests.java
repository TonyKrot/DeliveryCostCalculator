import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("selenide")
public class SelenideWebformTests {

    @BeforeEach
    void start() {
        open(Constants.BASE_URL1);
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }

    @Test
    void titleH1() {
        $("title").shouldHave(text("Hands-On Selenium WebDriver with Java"));
    }

    @Test
    void h5PracticeSite() {
        $("h5").shouldHave(text("Practice site"));
    }

    @Test
    void imageFluid() {
        $(".img-fluid").shouldBe(visible);
    }

    @Test
    void h1WebForm() {
        $("h1.display-6").shouldHave(text("Web form"));
    }

    @Test
    void labelTextInput() {
        $("#my-text-id").shouldBe(visible);
    }

    @Test
    void inputText() {
        $("#my-text-id").setValue("test").shouldHave(value("test"));
    }

    @Test
    void labelPassword() {
        $("label[for='my-password']").shouldHave(text("Password"));
    }

    @Test
    void inputPassword() {
        $("#my-password").setValue("12345").shouldHave(value("12345"));
    }

    @Test
    void labelTextarea() {
        $("label[for='my-textarea']").shouldHave(text("Textarea"));
    }

    @Test
    void textareaText() {
        $("#my-textarea").setValue("SomeTextHere").shouldHave(value("SomeTextHere"));
    }

    @Test
    void labelDisabledInput() {
        $("label[for='my-disabled']").shouldHave(text("Disabled input"));
        $("#my-disabled").shouldBe(disabled);
    }

    @Test
    void inputIsDisabled() {
        $("#my-disabled").shouldBe(disabled);
    }

    @Test
    void labelReadonlyInput() {
        $("label[for='my-readonly']").shouldHave(text("Readonly input"));
    }

    @Test
    void inputIsReadonly() {
        $("#my-readonly").shouldBe(readonly);
    }

    @Test
    void linkReturnIndex() {
        $(By.linkText("Return to index")).click();
        shouldBeUrl("https://bonigarcia.dev/selenium-webdriver-java/index.html");
    }

    private void shouldBeUrl(String url) {
    }

    @Test
    void spanCopyrightBoniGarc() {
        $(".text-muted").shouldHave(text("Copyright © 2021-2023 Boni García"));
    }

    @Test
    void linkBoniGarcia() {
        $("span a").click();
        shouldBeUrl("https://bonigarcia.dev/");
    }

    @Test
    public void testDropdownList() {
        $(By.name("my-select")).selectOptionByValue("Three");
        $(By.name("my-select")).shouldHave(value("Three"));
    }

    @Test
    public void testDataListDropdown() {
        $("#my-datalist").setValue("San Francisco");
        $("#my-datalist").shouldHave(value("San Francisco"));
    }

    @Test
    public void testFileUpload() {
        $("#my-file").uploadFromClasspath("test.js");
        $("#my-file").shouldHave(value("test.js"));
    }

    @Test
    public void testCheckedCheckbox() {
        $("#my-check-1").shouldBe(selected);
    }

    @Test
    public void testDefaultCheckbox() {
        $("#my-check-2").click();
        $("#my-check-2").shouldBe(selected);
    }

    @Test
    public void testCheckedRadio() {
        $("#my-radio-1").shouldBe(selected);
    }

    @Test
    public void testDefaultRadio() {
        $("#my-radio-2").click();
        $("#my-radio-2").shouldBe(selected);
    }

    @Test
    public void testSubmitButton() {
        $("button[type='submit']").click();
        $(".display-6").shouldHave(text("Form submitted"));
        $(".lead").shouldHave(text("Received"));
    }

    @Test
    public void testColorPicker() {
        $("#my-colors").click();
        $("#my-colors").shouldHave(value("#563d7c"));
    }

    @Test
    public void testDatePickerLabel() {
        $("#my-date").shouldBe(visible);
    }

    @Test
    void testDatePickerDay() {
        LocalDate today = LocalDate.now();
        $("#my-date").click();
        $(By.xpath("/html/body/div/div[1]/table/tbody/tr[2]/td[3]")).click();
        assertThat(today).isToday();
    }

    @Test
    public void testExampleRangeElementVisibility() {
        $("#my-range").shouldBe(visible);
        $("#my-range").shouldHave(value("5"));
    }
}
