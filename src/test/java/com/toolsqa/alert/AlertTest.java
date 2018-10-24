package com.toolsqa.alert;

import com.toolsqa.factory.PageObjectFactory;
import com.toolsqa.page.alert.AlertPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;

import static org.testng.Assert.assertEquals;

public class AlertTest extends AbstractTest {

    private static final String ALERT_URL = "http://toolsqa.com/handling-alerts-using-selenium-webdriver/";

    private AlertPage alertPage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        alertPage = PageObjectFactory.createAlertPage(driver);
        openAlertPage();
    }

    @Test
    public void test() {
        alertPage.clickSimpleAlertButton();
        alertPage.confirmAlert();

        alertPage.clickConfirmAlertButton();
        alertPage.confirmAlert();
        assertEquals(alertPage.getConfirmAlertText(), "You have clicked on OK");

        alertPage.clickConfirmAlertButton();
        alertPage.dismissAlert();
        assertEquals(alertPage.getConfirmAlertText(), "You have clicked on Cancel");

        alertPage.clickPromptAlertButton();
        assertEquals(alertPage.getAlertText(), "Do you like toolsqa?");
        alertPage.setAlertInput("No");
        alertPage.confirmAlert();
    }

    private void openAlertPage() {
        alertPage.getMenu().moveToDemoSites();
        alertPage.getMenu().goToAlertTestPage();
    }
}
