package com.toolsqa.page.iframe;

import com.toolsqa.page.AbstractPage;
import com.toolsqa.page.menu.MenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IframePracticePage extends AbstractPage {

    private MenuPage menu;

    @FindBy(css = "#IF1")
    private WebElement iFrame1;

    @FindBy(css = "input[name='firstname']")
    private WebElement iFrame1InputField;

    @FindBy(css = "#submit")
    private WebElement iFrame1SubmitButton;

    @FindBy(css = "#IF2")
    private WebElement iFrame2;

    @FindBy(css = "a.btn-primary")
    private WebElement iFrame2SubmitButton;

    @FindBy(css = "#comments")
    private WebElement iFrame2HiddenDiv;

    public IframePracticePage(WebDriver driver, WebDriverWait wait, Actions actions, MenuPage menu) {
        super(driver, wait, actions);
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public MenuPage getMenu() {
        return menu;
    }

    public void switchToIframe1() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame1));
    }

    public void setIframe1Input(String input) {
        waitUntilVisible(iFrame1InputField).sendKeys(input);
    }

    public String getIframe1InputText() {
        return waitUntilVisible(iFrame1InputField).getAttribute("value");
    }

    public void clickIframe1SubmitButton() {
        waitUntilClickable(iFrame1SubmitButton).click();
    }

    public void switchToIframe2() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame2));
    }

    public void clickIframe2SubmitButton() {
        waitUntilClickable(iFrame2SubmitButton).click();
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void isVisible() {
        waitUntilVisible(iFrame2HiddenDiv);
    }
}
