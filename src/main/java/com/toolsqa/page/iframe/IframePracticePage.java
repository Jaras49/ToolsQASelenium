package com.toolsqa.page.iframe;

import com.toolsqa.annotation.WaitUntilVisible;
import com.toolsqa.page.AbstractPage;
import com.toolsqa.page.menu.MenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IframePracticePage extends AbstractPage<IframePracticePage> {

    private MenuPage menu;

    @WaitUntilVisible
    @FindBy(css = "#IF1")
    private WebElement formFrame;

    @FindBy(css = "input[name='firstname']")
    private WebElement formFrameInputFirld;

    @FindBy(css = "#submit")
    private WebElement formFrameSubmitButton;

    @WaitUntilVisible
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
        waitUntilPageLoads();
    }

    public MenuPage getMenu() {
        return menu;
    }

    public IframePracticePage switchToFormFrame() {
        driver.switchTo().frame(formFrame);
        return this;
    }

    public IframePracticePage setFormFrameInput(String input) {
        formFrameInputFirld.sendKeys(input);
        return this;
    }

    public String getFormFrameInputText() {
        return formFrameInputFirld.getAttribute("value");
    }

    public IframePracticePage clickFormFrameSubmitButton() {
        formFrameSubmitButton.click();
        return this;
    }

    public IframePracticePage switchToBlogFrame() {
        driver.switchTo().frame(blogFrame);
        return this;
    }

    public IframePracticePage clickBlogFrameSubmitButton() {
        blogFrameSubmitButton.click();
        waitUntilVisible(blogFrameHiddenCommentsDiv);
        return this;
    }

    public IframePracticePage switchToParentFrame() {
        driver.switchTo().parentFrame();
        return this;
    }
}
