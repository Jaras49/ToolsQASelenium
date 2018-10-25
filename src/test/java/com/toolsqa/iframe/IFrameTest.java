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
        iframePracticePage.switchToIframe1();
        iframePracticePage.setIframe1Input("test");
        assertEquals(iframePracticePage.getIframe1InputText(), "test");
        iframePracticePage.clickIframe1SubmitButton();
        assertEquals(iframePracticePage.getIframe1InputText(), "");

        iframePracticePage.switchToParentFrame();

        iframePracticePage.switchToIframe2();
        iframePracticePage.clickIframe2SubmitButton();
        iframePracticePage.isVisible();
    }

    private void openIFramePracticePage() {
        iframePracticePage.getMenu().hoverOverDemoSites();
        iframePracticePage.getMenu().goToIframePracticePage();

    }
}
