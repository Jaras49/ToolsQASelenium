package com.toolsqa.iframe;

import com.toolsqa.factory.PageObjectFactory;
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
        iframePracticePage = PageObjectFactory.createIframePracticePage(driver);
        openIFramePracticePage();
    }

    @Test
    public void test() {
        iframePracticePage.switchToFormFrame();
        iframePracticePage.setFormFrameInput("test");
        assertEquals(iframePracticePage.getFormFrameInputText(), "test");
        iframePracticePage.clickFormFrameSubmitButton();
        assertEquals(iframePracticePage.getFormFrameInputText(), "");

        iframePracticePage.switchToParentFrame();

        iframePracticePage.switchToBlogFrame();
        iframePracticePage.clickBlogFrameSubmitButton();
        iframePracticePage.isCommentsDivVisible();
    }

    private void openIFramePracticePage() {
        iframePracticePage.getMenu().hoverOverDemoSites();
        iframePracticePage.getMenu().goToIframePracticePage();

    }
}
