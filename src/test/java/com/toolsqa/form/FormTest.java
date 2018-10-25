package com.toolsqa.form;

import com.toolsqa.factory.PageObjectFactory;
import com.toolsqa.page.form.AutomationFormPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FormTest extends AbstractTest {

    private static final String DUMMY_PHOTO_NAME = "form/dummyPhoto.jpg";

    private AutomationFormPage automationFormPage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        automationFormPage = PageObjectFactory.createAutomationFormPage(driver);
        openForm();
    }

    @Test
    public void formTest() {
        fillForm();
        assertFormData();
        automationFormPage.clickSubmitButton();
        assertTrue(automationFormPage.getFirstNameInput().isEmpty());
    }

    private void openForm() {
        automationFormPage.getMenu().hoverOverDemoSites();
        automationFormPage.getMenu().goToAutomationFormPage();
    }

    private void fillForm() {
        automationFormPage.setFirstNameInput("Jarosław");
        automationFormPage.setLastNameInput("Nowak");

        automationFormPage.clickFemaleSexRadioButton();

        automationFormPage.clickExperienceRadioButton(1);

        automationFormPage.setDateInput("1.08.1889");

        automationFormPage.clickAutomationTesterProffesionCheckBox();

        String dummyPhotoPath = this.getClass().getClassLoader().getResource(DUMMY_PHOTO_NAME).getPath().replaceFirst("/", "");
        automationFormPage.setPhotoInput(dummyPhotoPath);

        automationFormPage.clickSeleniumWebDriverAutomationToolCheckBox();

        automationFormPage.selectContinentByIndex(1);

        automationFormPage.selectSeleniumCommandsByIndex(0, 3);
    }

    private void assertFormData() {
        assertEquals(automationFormPage.getFirstNameInput(), "Jarosław");
        assertEquals(automationFormPage.getLastNameInput(), "Nowak");

        assertTrue(automationFormPage.isFemaleSexRadioButtonSelected());
        assertFalse(automationFormPage.isMaleSexRadioButtonSelected());

        assertEquals(automationFormPage.getSelectedExperienceRadioButtonValue(), 1);

        assertEquals(automationFormPage.getDateInput(), "1.08.1889");

        assertTrue(automationFormPage.isAutomationTesterProffesionCheckBoxSelected());
        assertFalse(automationFormPage.isManualTesterProffesionCheckBoxSelected());

        assertEquals(automationFormPage.getPhotoName(), "dummyPhoto.jpg");

        assertTrue(automationFormPage.isSeleniumWebDriverAutomationToolCheckBoxSelected());
        assertFalse(automationFormPage.isQptAutomationToolCheckBoxSelected());
        assertFalse(automationFormPage.isSeleniumIdeAutomationToolCheckBoxSelected());

        assertEquals(automationFormPage.getSelectedContinentName(), "Europe");

        assertEquals(automationFormPage.getSelectedSeleniumCommands(), Arrays.asList("Browser Commands", "Wait Commands"));
    }
}
