package com.toolsqa.form;

import com.toolsqa.page.form.AutomationFormPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

public class FormTest extends AbstractTest {

    private static final String DUMMY_PHOTO_NAME = "form/dummyPhoto.jpg";

    private AutomationFormPage automationFormPage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        automationFormPage = openForm();
    }

    @Test
    public void formTest() {
        fillForm();
        assertFormData();
        automationFormPage.clickSubmitButton();
        assertTrue(automationFormPage.getFirstNameInput().isEmpty());
    }

    private AutomationFormPage openForm() {
        return menuPage.hoverOverDemoSites()
                .goToAutomationFormPage();
    }

    private void fillForm() {
        String dummyPhotoPath = this.getClass().getClassLoader().getResource(DUMMY_PHOTO_NAME).getPath().replaceFirst("/", "");
        automationFormPage.setFirstNameInput("Jarosław")
                .setLastNameInput("Nowak")
                .clickFemaleSexRadioButton()
                .clickExperienceRadioButton(1)
                .setDateInput("1.08.1889")
                .clickAutomationTesterProffesionCheckBox()
                .setPhotoInput(dummyPhotoPath)
                .clickSeleniumWebDriverAutomationToolCheckBox()
                .selectContinentByIndex(1)
                .selectSeleniumCommandsByIndex(0, 3);
    }

    private void assertFormData() {
        automationFormPage
                .assertEquals(automationFormPage.getFirstNameInput(), "Jarosław", automationFormPage)
                .assertEquals(automationFormPage.getLastNameInput(), "Nowak", automationFormPage)
                .assertTrue(automationFormPage.isFemaleSexRadioButtonSelected(), automationFormPage)
                .assertFalse(automationFormPage.isMaleSexRadioButtonSelected(), automationFormPage)
                .assertEquals(automationFormPage.getSelectedExperienceRadioButtonValue(), 1, automationFormPage)
                .assertEquals(automationFormPage.getDateInput(), "1.08.1889", automationFormPage)
                .assertTrue(automationFormPage.isAutomationTesterProffesionCheckBoxSelected(), automationFormPage)
                .assertFalse(automationFormPage.isManualTesterProffesionCheckBoxSelected(), automationFormPage)
                .assertEquals(automationFormPage.getPhotoName(), "dummyPhoto.jpg", automationFormPage)
                .assertTrue(automationFormPage.isSeleniumWebDriverAutomationToolCheckBoxSelected(), automationFormPage)
                .assertFalse(automationFormPage.isQptAutomationToolCheckBoxSelected(), automationFormPage)
                .assertFalse(automationFormPage.isSeleniumIdeAutomationToolCheckBoxSelected(),automationFormPage )
                .assertEquals(automationFormPage.getSelectedContinentName(), "Europe", automationFormPage)
                .assertEquals(automationFormPage.getSelectedSeleniumCommands(), Arrays.asList("Browser Commands", "Wait Commands"), automationFormPage);
    }
}
