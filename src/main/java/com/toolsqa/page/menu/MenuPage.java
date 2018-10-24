package com.toolsqa.page.menu;

import com.toolsqa.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage extends AbstractPage {

    @FindBy(css = "#primary-menu")
    private WebElement primaryMenu;

    @FindBy(xpath = "//*[@id='primary-menu']//*[contains(text(), 'DEMO SITES')]")
    private WebElement demoSites;

    @FindBy(xpath = "//*[@id='primary-menu']//*[contains(text(), 'Automation Practice Form')]")
    private WebElement automationPracticeForm;

    @FindBy(xpath = "//*[@id='primary-menu']//*[contains(text(), 'Alerts')]")
    private WebElement alertTestPage;

    public MenuPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
        PageFactory.initElements(driver, this);
    }

    public void moveToDemoSites() {
        actions.moveToElement(waitUntilClickable(demoSites)).perform();
    }

    public void goToAutomationFormPage() {
        waitUntilClickable(automationPracticeForm).click();
    }

    public void goToAlertTestPage() {
        waitUntilClickable(alertTestPage).click();
    }
}
