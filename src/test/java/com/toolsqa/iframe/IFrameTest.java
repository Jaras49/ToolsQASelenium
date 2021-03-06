package com.toolsqa.iframe;

import com.toolsqa.page.iframe.IframePracticePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;

public class IFrameTest extends AbstractTest {

    private IframePracticePage iframePracticePage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        iframePracticePage = openIFramePracticePage();
    }

    @Test
    public void iFrameTest() {
        iframePracticePage.switchToFormFrame()
                .setFormFrameInput("test")
                .assertEquals(iframePracticePage.getFormFrameInputText(), "test")
                .clickFormFrameSubmitButton()
                .assertEquals(iframePracticePage.getFormFrameInputText(), "")
                .switchToParentFrame()
                .switchToBlogFrame()
                .clickBlogFrameSubmitButton();
    }

    private IframePracticePage openIFramePracticePage() {
        return menuPage.hoverOverDemoSites()
                .goToIframePracticePage();
    }
}
