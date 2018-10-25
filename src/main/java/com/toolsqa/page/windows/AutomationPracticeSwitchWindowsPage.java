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

    public void clickNewBrowserWindowButton() {
        waitUntilClickable(newBrowserWindowButton).click();
    }

    public void clickNewMessageWindowButton() {
        waitUntilClickable(newMessageWindowButton).click();
    }

    public void clickNewBrowserTabButton() {
        waitUntilClickable(newBrowserTabButton).click();
    }

    public MenuPage getMenu() {
        return menu;
    }
}
