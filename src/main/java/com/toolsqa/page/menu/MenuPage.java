package com.toolsqa.page.menu;

import com.toolsqa.factory.PageObjectFactory;
import com.toolsqa.page.AbstractPage;
import com.toolsqa.page.alert.AlertPage;
import com.toolsqa.page.form.AutomationFormPage;
import com.toolsqa.page.iframe.IframePracticePage;
import com.toolsqa.page.table.AutomationPracticeTablePage;
import com.toolsqa.page.windows.AutomationPracticeSwitchWindowsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage extends AbstractPage<MenuPage> {

    @FindBy(css = "#primary-menu")
    private WebElement primaryMenu;

    @FindBy(xpath = "//*[@id='primary-menu']//*[contains(text(), 'DEMO SITES')]")
    private WebElement demoSites;

    @FindBy(xpath = "//*[@id='primary-menu']//*[contains(text(), 'Automation Practice Form')]")
    private WebElement automationPracticeForm;

    @FindBy(xpath = "//*[@id='primary-menu']//*[contains(text(), 'Alerts')]")
    private WebElement alertTestPage;

    @FindBy(xpath = "//*[@id='primary-menu']//*[contains(text(), 'Automation Practice Table')]")
    private WebElement automationPracticeTable;

    @FindBy(xpath = "//*[@id='primary-menu']//*[contains(text(), 'Automation Practice Switch Windows')]")
    private WebElement automationPracticeSwitchWindows;

    @FindBy(xpath = "//*[@id='primary-menu']//*[contains(text(), 'IFrame practice page')]")
    private WebElement iFramePracticePage;

    public MenuPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
        PageFactory.initElements(driver, this);
    }

    public MenuPage hoverOverDemoSites() {
        actions.moveToElement(demoSites).perform();
        return this;
    }

    public AutomationFormPage goToAutomationFormPage() {
        waitUntilClickable(automationPracticeForm).click();
        return PageObjectFactory.createAutomationFormPage(driver);
    }

    public AlertPage goToAlertTestPage() {
        waitUntilClickable(alertTestPage).click();
        return PageObjectFactory.createAlertPage(driver);
    }

    public AutomationPracticeTablePage goToAutomationPracticeTablePage() {
        waitUntilClickable(automationPracticeTable).click();
        return PageObjectFactory.createAutomationPracticeTablePage(driver);
    }

    public AutomationPracticeSwitchWindowsPage goToAutomationPracticeSwitchWindows() {
        waitUntilClickable(automationPracticeSwitchWindows).click();
        return PageObjectFactory.createAutomationSwitchWindowsPage(driver);
    }

    public IframePracticePage goToIframePracticePage() {
        waitUntilClickable(iFramePracticePage).click();
        return PageObjectFactory.createIframePracticePage(driver);
    }
}
