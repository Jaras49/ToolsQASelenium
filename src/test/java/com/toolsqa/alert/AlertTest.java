package com.toolsqa.alert;

import com.toolsqa.page.alert.AlertPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;


public class AlertTest extends AbstractTest {

    private AlertPage alertPage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        alertPage = openAlertPage();
    }

    @Test
    public void alertTest() {
        alertPage.clickSimpleAlertButton()
                .confirmAlert()
                .clickConfirmAlertButton()
                .confirmAlert()
                .assertEquals(alertPage.getConfirmAlertText(), "You have clicked on OK")
                .clickConfirmAlertButton()
                .dismissAlert()
                .assertEquals(alertPage.getConfirmAlertText(), "You have clicked on Cancel")
                .clickPromptAlertButton()
                .assertEquals(alertPage.getAlertText(), "Do you like toolsqa?")
                .setAlertInput("No")
                .confirmAlert();
    }

    private AlertPage openAlertPage() {
        return menuPage.hoverOverDemoSites()
                .goToAlertTestPage();
    }
}
