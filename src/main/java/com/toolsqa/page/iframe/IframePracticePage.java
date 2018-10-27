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
    private WebElement formFrame;

    @FindBy(css = "input[name='firstname']")
    private WebElement formFrameInputFirld;

    @FindBy(css = "#submit")
    private WebElement formFrameSubmitButton;

    @FindBy(css = "#IF2")
    private WebElement blogFrame;

    @FindBy(css = "a.btn-primary")
    private WebElement blogFrameSubmitButton;

    @FindBy(css = "#comments")
    private WebElement blogFrameHiddenCommentsDiv;

    public IframePracticePage(WebDriver driver, WebDriverWait wait, Actions actions, MenuPage menu) {
        super(driver, wait, actions);
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public MenuPage getMenu() {
        return menu;
    }

    public IframePracticePage switchToFormFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(formFrame));
        return this;
    }

    public IframePracticePage setFormFrameInput(String input) {
        waitUntilVisible(formFrameInputFirld).sendKeys(input);
        return this;
    }

    public String getFormFrameInputText() {
        return waitUntilVisible(formFrameInputFirld).getAttribute("value");
    }

    public IframePracticePage clickFormFrameSubmitButton() {
        waitUntilClickable(formFrameSubmitButton).click();
        return this;
    }

    public IframePracticePage switchToBlogFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(blogFrame));
        return this;
    }

    public IframePracticePage clickBlogFrameSubmitButton() {
        waitUntilClickable(blogFrameSubmitButton).click();
        return this;
    }

    public IframePracticePage switchToParentFrame() {
        driver.switchTo().parentFrame();
        return this;
    }

    public IframePracticePage isCommentsDivVisible() {
        waitUntilVisible(blogFrameHiddenCommentsDiv);
        return this;
    }
}
