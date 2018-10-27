package com.toolsqa.factory;

import com.toolsqa.page.alert.AlertPage;
import com.toolsqa.page.form.AutomationFormPage;
import com.toolsqa.page.iframe.IframePracticePage;
import com.toolsqa.page.menu.MenuPage;
import com.toolsqa.page.table.AutomationPracticeTablePage;
import com.toolsqa.page.windows.AutomationPracticeSwitchWindowsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectFactory {

    private static final long WAIT_SECONDS = 10L;

    private PageObjectFactory() {
    }

    public static AutomationFormPage createAutomationFormPage(WebDriver driver) {
        return new AutomationFormPage
                (driver, new WebDriverWait(driver, WAIT_SECONDS), new Actions(driver), createMenuPage(driver));
    }

    public static AlertPage createAlertPage(WebDriver driver) {
        return new AlertPage
                (driver, new WebDriverWait(driver, WAIT_SECONDS), new Actions(driver), createMenuPage(driver));
    }

    public static AutomationPracticeTablePage createAutomationPracticeTable(WebDriver driver) {
        return new AutomationPracticeTablePage
                (driver, new WebDriverWait(driver, WAIT_SECONDS), new Actions(driver), createMenuPage(driver));
    }

    public static AutomationPracticeSwitchWindowsPage createAutomationSwitchWindowsPage(WebDriver driver) {
        return new AutomationPracticeSwitchWindowsPage
                (driver, new WebDriverWait(driver, WAIT_SECONDS), new Actions(driver), createMenuPage(driver));
    }

    public static IframePracticePage createIframePracticePage(WebDriver driver) {
        return new IframePracticePage
                (driver, new WebDriverWait(driver, WAIT_SECONDS), new Actions(driver), createMenuPage(driver));
    }

    public static MenuPage createMenuPage(WebDriver driver) {
        return new MenuPage
                (driver, new WebDriverWait(driver, WAIT_SECONDS), new Actions(driver));
    }
}
