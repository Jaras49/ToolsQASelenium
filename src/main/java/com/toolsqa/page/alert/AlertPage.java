package com.toolsqa.page.alert;

import com.toolsqa.annotation.WaitUntilVisible;
import com.toolsqa.page.AbstractPage;
import com.toolsqa.page.menu.MenuPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertPage extends AbstractPage<AlertPage> {

    private MenuPage menuPage;

    @WaitUntilVisible
    @FindBy(css = "button[onClick='pushAlert()']")
    private WebElement simpleAlertButton;

    @WaitUntilVisible
    @FindBy(css = "button[onClick='pushConfirm()']")
    private WebElement confirmAlertButton;

    @WaitUntilVisible
    @FindBy(css = "button[onClick='promptConfirm()']")
    private WebElement promtAlerButton;

    @FindBy(css = "#ConfirmOption")
    private WebElement confirmAlertText;

    public AlertPage(WebDriver driver, WebDriverWait wait, Actions actions, MenuPage menuPage) {
        super(driver, wait, actions);
        this.menuPage = menuPage;
        PageFactory.initElements(driver, this);
        waitUntilPageLoads();
    }

    public AlertPage clickSimpleAlertButton() {
        scrollPage();
        simpleAlertButton.click();
        return this;
    }

    public AlertPage clickConfirmAlertButton() {
        scrollPage();
        confirmAlertButton.click();
        return this;
    }

    public AlertPage clickPromptAlertButton() {
        scrollPage();
        promtAlerButton.click();
        return this;
    }

    public AlertPage confirmAlert() {
        waitUntilAlertPresent().accept();
        return this;
    }

    public AlertPage dismissAlert() {
        waitUntilAlertPresent().dismiss();
        return this;
    }

    public String getConfirmAlertText() {
        return waitUntilVisible(confirmAlertText).getText();
    }

    public String getAlertText() {
        return waitUntilAlertPresent().getText();
    }

    public AlertPage setAlertInput(String input) {
        waitUntilAlertPresent().sendKeys(input);
        return this;
    }

    public MenuPage getMenu() {
        return menuPage;
    }

    private void scrollPage() {
        ((JavascriptExecutor) driver).executeScript("scroll(0, 400);");
    }
}
