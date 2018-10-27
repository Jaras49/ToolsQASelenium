package com.toolsqa.page.windows;

import com.toolsqa.page.AbstractPage;
import com.toolsqa.page.menu.MenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationPracticeSwitchWindowsPage extends AbstractPage {

    private MenuPage menu;

    @FindBy(css = "button[onClick='newBrwWin()']")
    private WebElement newBrowserWindowButton;

    @FindBy(css = "button[onClick='newMsgWin()']")
    private WebElement newMessageWindowButton;

    @FindBy(css = "button[onClick='newBrwTab()']")
    private WebElement newBrowserTabButton;

    public AutomationPracticeSwitchWindowsPage(WebDriver driver, WebDriverWait wait, Actions actions, MenuPage menu) {
        super(driver, wait, actions);
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public AutomationPracticeSwitchWindowsPage clickNewBrowserWindowButton() {
        waitUntilClickable(newBrowserWindowButton).click();
        return this;
    }

    public AutomationPracticeSwitchWindowsPage clickNewMessageWindowButton() {
        waitUntilClickable(newMessageWindowButton).click();
        return this;
    }

    public AutomationPracticeSwitchWindowsPage clickNewBrowserTabButton() {
        waitUntilClickable(newBrowserTabButton).click();
        return this;
    }

    public MenuPage getMenu() {
        return menu;
    }
}
