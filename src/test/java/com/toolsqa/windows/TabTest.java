package com.toolsqa.windows;

import com.toolsqa.factory.PageObjectFactory;
import com.toolsqa.page.windows.AutomationPracticeSwitchWindowsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;

import static org.testng.Assert.assertEquals;

public class TabTest extends AbstractTest {

    private AutomationPracticeSwitchWindowsPage automationPracticeSwitchWindowsPage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        automationPracticeSwitchWindowsPage = PageObjectFactory.createAutomationSwitchWindowsPage(driver);
        openAutomationPracticeSwitchWindowsPage();
    }

    @Test
    public void switchWindowTest() {
        automationPracticeSwitchWindowsPage.clickNewBrowserWindowButton();
        switchAndAssert();
    }

    @Test
    public void switchTabTest() {
        automationPracticeSwitchWindowsPage.clickNewBrowserTabButton();
        switchAndAssert();
    }

    @Test
    public void switchMessageWindowTest() {
        automationPracticeSwitchWindowsPage.clickNewMessageWindowButton();
        automationPracticeSwitchWindowsPage.switchToNewWindow(1);

        String text = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body"))).getText();
        assertEquals(text, "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.");

        driver.close();
    }

    private void switchAndAssert() {
        automationPracticeSwitchWindowsPage.switchToNewWindow(1);

        String url = driver.getCurrentUrl();
        automationPracticeSwitchWindowsPage.getMenu().hoverOverDemoSites();
        automationPracticeSwitchWindowsPage.getMenu().goToAutomationPracticeTablePage();
        new WebDriverWait(driver, 15).until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
        assertEquals(driver.getCurrentUrl(), "http://toolsqa.com/automation-practice-table/");

        driver.close();
        automationPracticeSwitchWindowsPage.switchToNewWindow(0);
        assertEquals(driver.getCurrentUrl(), "http://toolsqa.com/automation-practice-switch-windows/");
    }

    private void openAutomationPracticeSwitchWindowsPage() {
        automationPracticeSwitchWindowsPage.getMenu().hoverOverDemoSites();
        automationPracticeSwitchWindowsPage.getMenu().goToAutomationPracticeSwitchWindows();
    }
}
