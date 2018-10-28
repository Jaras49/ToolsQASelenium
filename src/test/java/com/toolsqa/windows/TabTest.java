package com.toolsqa.windows;

import com.toolsqa.page.windows.AutomationPracticeSwitchWindowsPage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;

public class TabTest extends AbstractTest {

    private AutomationPracticeSwitchWindowsPage automationPracticeSwitchWindowsPage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        automationPracticeSwitchWindowsPage = openAutomationPracticeSwitchWindowsPage();
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
        automationPracticeSwitchWindowsPage.clickNewMessageWindowButton()
                .switchToNewWindow(1)
                .assertEquals(driver.findElement(By.tagName("body")).getText(),
                        "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.");
        driver.close();
    }

    private void switchAndAssert() {
        automationPracticeSwitchWindowsPage.switchToNewWindow(1)
                .getMenu()
                .hoverOverDemoSites()
                .goToAutomationPracticeTablePage()
                .assertEquals(driver.getCurrentUrl(), "http://toolsqa.com/automation-practice-table/");
        driver.close();
        automationPracticeSwitchWindowsPage.switchToNewWindow(0)
                .assertEquals(driver.getCurrentUrl(), "http://toolsqa.com/automation-practice-switch-windows/");
    }

    private AutomationPracticeSwitchWindowsPage openAutomationPracticeSwitchWindowsPage() {
        return menuPage.hoverOverDemoSites()
                .goToAutomationPracticeSwitchWindows();
    }
}
