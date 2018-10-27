package com.toolsqa.alert;

import com.toolsqa.page.alert.AlertPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;

import static org.testng.Assert.assertEquals;

public class AlertTest extends AbstractTest {

    private AlertPage alertPage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        alertPage = openAlertPage();
    }

    @Test
    public void test() {
        alertPage.clickSimpleAlertButton()
                .confirmAlert()
                .clickConfirmAlertButton()
                .confirmAlert();
        assertEquals(alertPage.getConfirmAlertText(), "You have clicked on OK");

        alertPage.clickConfirmAlertButton()
                .dismissAlert();
        assertEquals(alertPage.getConfirmAlertText(), "You have clicked on Cancel");

        alertPage.clickPromptAlertButton();
        assertEquals(alertPage.getAlertText(), "Do you like toolsqa?");
        alertPage.setAlertInput("No")
                .confirmAlert();
    }

    private AlertPage openAlertPage() {
        return menuPage.hoverOverDemoSites()
                .goToAlertTestPage();
    }
}
