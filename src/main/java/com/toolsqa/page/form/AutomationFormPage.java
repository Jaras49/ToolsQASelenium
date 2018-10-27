package com.toolsqa.page.form;

import com.toolsqa.page.AbstractPage;
import com.toolsqa.page.menu.MenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class AutomationFormPage extends AbstractPage {

    private static final String VALUE_ATTR = "value";

    private MenuPage menu;

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(id = "sex-0")
    private WebElement maleSexRadioButton;

    @FindBy(id = "sex-1")
    private WebElement femaleSexRadioButton;

    @FindBy(css = ".control-group [name='exp']")
    private List<WebElement> experienceRadioButtons;

    @FindBy(css = "#datepicker")
    private WebElement dateInput;

    @FindBy(css = "[value='Manual Tester']")
    private WebElement manualTesterProffesionCheckBox;

    @FindBy(css = "[value='Automation Tester']")
    private WebElement automationTesterProffesionCheckBox;

    @FindBy(css = "#photo")
    private WebElement photoInput;

    @FindBy(css = "#tool-0")
    private WebElement qtpAutomationToolCheckBox;

    @FindBy(css = "#tool-1")
    private WebElement seleniumIdeAutomationToolCheckBox;

    @FindBy(css = "#tool-2")
    private WebElement seleniumWebDriverAutomationToolCheckBox;

    @FindBy(css = "#continents")
    private WebElement continentsSelect;

    @FindBy(css = "#selenium_commands")
    private WebElement seleniumCommandsSelect;

    @FindBy(css = "#submit")
    private WebElement submitButton;

    public AutomationFormPage(WebDriver driver, WebDriverWait wait, Actions actions, MenuPage menu) {
        super(driver, wait, actions);
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public AutomationFormPage setFirstNameInput(String firstname) {
        waitUntilClickable(firstNameInput).sendKeys(firstname);
        return this;
    }

    public String getFirstNameInput() {
        return firstNameInput.getAttribute(VALUE_ATTR);
    }

    public AutomationFormPage setLastNameInput(String lastname) {
        waitUntilClickable(lastNameInput).sendKeys(lastname);
        return this;
    }

    public String getLastNameInput() {
        return lastNameInput.getAttribute(VALUE_ATTR);
    }

    public AutomationFormPage clickMaleSexRadioButton() {
        waitUntilClickable(maleSexRadioButton).click();
        return this;
    }

    public boolean isMaleSexRadioButtonSelected() {
        return maleSexRadioButton.isSelected();
    }

    public AutomationFormPage clickFemaleSexRadioButton() {
        waitUntilClickable(femaleSexRadioButton).click();
        return this;
    }

    public boolean isFemaleSexRadioButtonSelected() {
        return femaleSexRadioButton.isSelected();
    }

    public AutomationFormPage clickExperienceRadioButton(int value) {
        waitUntilVisible(experienceRadioButtons).stream()
                .filter(button -> button.getAttribute(VALUE_ATTR).equals(String.valueOf(value)))
                .findAny()
                .ifPresent(WebElement::click);
        return this;
    }

    public int getSelectedExperienceRadioButtonValue() {
        return experienceRadioButtons.stream()
                .filter(WebElement::isSelected)
                .mapToInt(button -> Integer.valueOf(button.getAttribute(VALUE_ATTR)))
                .findAny()
                .orElse(0);
    }

    public AutomationFormPage setDateInput(String date) {
        waitUntilClickable(dateInput).sendKeys(date);
        return this;
    }

    public String getDateInput() {
        return dateInput.getAttribute(VALUE_ATTR);
    }

    public AutomationFormPage clickManualTesterProffesionCheckBox() {
        waitUntilClickable(manualTesterProffesionCheckBox).click();
        return this;
    }

    public boolean isManualTesterProffesionCheckBoxSelected() {
        return manualTesterProffesionCheckBox.isSelected();
    }

    public AutomationFormPage clickAutomationTesterProffesionCheckBox() {
        waitUntilClickable(automationTesterProffesionCheckBox).click();
        return this;

    }

    public boolean isAutomationTesterProffesionCheckBoxSelected() {
        return automationTesterProffesionCheckBox.isSelected();
    }

    public AutomationFormPage setPhotoInput(String photoPath) {
        waitUntilClickable(photoInput).sendKeys(photoPath);
        return this;
    }

    public String getPhotoName() {
        return photoInput.getAttribute(VALUE_ATTR).replaceAll(".*\\\\", "");
    }

    public AutomationFormPage clickQtpAutomationToolCheckBox() {
        waitUntilClickable(qtpAutomationToolCheckBox).click();
        return this;
    }

    public boolean isQptAutomationToolCheckBoxSelected() {
        return qtpAutomationToolCheckBox.isSelected();
    }

    public AutomationFormPage clickSeleniumIdeAutomationToolCheckBox() {
        waitUntilClickable(seleniumIdeAutomationToolCheckBox).click();
        return this;
    }

    public boolean isSeleniumIdeAutomationToolCheckBoxSelected() {
        return seleniumIdeAutomationToolCheckBox.isSelected();
    }

    public AutomationFormPage clickSeleniumWebDriverAutomationToolCheckBox() {
        waitUntilClickable(seleniumWebDriverAutomationToolCheckBox).click();
        return this;
    }

    public boolean isSeleniumWebDriverAutomationToolCheckBoxSelected() {
        return seleniumWebDriverAutomationToolCheckBox.isSelected();
    }

    public AutomationFormPage selectContinentByIndex(int index) {
        waitUntilClickable(continentsSelect);
        new Select(continentsSelect).selectByIndex(index);
        return this;
    }

    public String getSelectedContinentName() {
        return new Select(continentsSelect).getFirstSelectedOption().getText();
    }

    public AutomationFormPage selectSeleniumCommandsByIndex(int... index) {
        waitUntilClickable(seleniumCommandsSelect);
        Select select = new Select(seleniumCommandsSelect);
        for (int i : index) {
            select.selectByIndex(i);
        }
        return this;
    }

    public List<String> getSelectedSeleniumCommands() {
        Select select = new Select(seleniumCommandsSelect);

        return select.getAllSelectedOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public AutomationFormPage clickSubmitButton() {
        waitUntilClickable(submitButton).click();
        return this;
    }

    public MenuPage getMenu() {
        return menu;
    }
}
