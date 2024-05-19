package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebFormPage extends BasePage {

    public WebFormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "my-text")
    private WebElement textInput;

    @FindBy(name = "my-password")
    private WebElement passwordInput;

    @FindBy(name = "my-textarea")
    private WebElement textarea;

    @FindBy(name = "my-disabled")
    private WebElement disabledInput;

    @FindBy(name = "my-readonly")
    private WebElement readonlyInput;

    @FindBy(linkText = "Return to index")
    private WebElement linkReturnIndex;

    @FindBy(name = "my-select")
    private WebElement dropdownSelect;

    @FindBy(name = "my-datalist")
    private WebElement dropdownDatalist;

    @FindBy(name = "my-file")
    private WebElement fileInput;

    @FindBy(id = "my-check-1")
    private WebElement checkedCheckbox;

    @FindBy(id = "my-check-2")
    private WebElement defaultCheckbox;

    @FindBy(id = "my-radio-1")
    private WebElement checkedRadio;

    @FindBy(id = "my-radio-2")
    private WebElement defaultRadio;

    @FindBy(name = "my-colors")
    private WebElement colorPicker;

    @FindBy(name = "my-date")
    private WebElement datePicker;

    @FindBy(name = "my-range")
    private WebElement rangeInput;

    @FindBy(name = "my-hidden")
    private WebElement hiddenInput;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    // getters
    public WebElement getTextInput() {
        return textInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getTextarea() {
        return textarea;
    }

    public WebElement getDisabledInput() {
        return disabledInput;
    }

    public WebElement getReadonlyInput() {
        return readonlyInput;
    }

    public WebElement getLinkReturnIndex() {
        return linkReturnIndex;
    }

    public WebElement getDropdownSelect() {
        return dropdownSelect;
    }

    public WebElement getDropdownDatalist() {
        return dropdownDatalist;
    }

    public WebElement getFileInput() {
        return fileInput;
    }

    public WebElement getCheckedCheckbox() {
        return checkedCheckbox;
    }

    public WebElement getDefaultCheckbox() {
        return defaultCheckbox;
    }

    public WebElement getCheckedRadio() {
        return checkedRadio;
    }

    public WebElement getDefaultRadio() {
        return defaultRadio;
    }

    public WebElement getColorPicker() {
        return colorPicker;
    }

    public WebElement getDatePicker() {
        return datePicker;
    }

    public WebElement getRangeInput() {
        return rangeInput;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getHiddenInput() {
        return hiddenInput;
    }
}



