package com.toolsqa.iframe;

import com.toolsqa.page.iframe.IframePracticePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;

import static org.testng.Assert.assertEquals;

public class IFrameTest extends AbstractTest {

    private IframePracticePage iframePracticePage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        iframePracticePage = openIFramePracticePage();
    }

    @Test
    public void test() {
        iframePracticePage.switchToFormFrame()
                .setFormFrameInput("test");
        assertEquals(iframePracticePage.getFormFrameInputText(), "test");

        iframePracticePage.clickFormFrameSubmitButton();
        assertEquals(iframePracticePage.getFormFrameInputText(), "");

        iframePracticePage.switchToParentFrame()
                .switchToBlogFrame()
                .clickBlogFrameSubmitButton()
                .isCommentsDivVisible();
    }

    private IframePracticePage openIFramePracticePage() {
        return menuPage.hoverOverDemoSites()
                .goToIframePracticePage();
    }
}
