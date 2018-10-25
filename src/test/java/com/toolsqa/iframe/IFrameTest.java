package com.toolsqa.iframe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class IFrameTest extends AbstractTest {

    private static final String TOOLS_QA_URL = "http://toolsqa.com/";

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        driver.get(TOOLS_QA_URL);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS).implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void iFrameTest() throws InterruptedException{
        openIFramePracticePage();

        WebElement iFrame1 = driver.findElement(By.cssSelector("#IF1"));
        driver.switchTo().frame(iFrame1);
        driver.findElement(By.cssSelector("#submit")).click();

        driver.switchTo().parentFrame();

        WebElement iFrame2 = driver.findElement(By.cssSelector("#IF2"));
        driver.switchTo().frame(iFrame2);
        driver.findElement(By.cssSelector("a.btn-primary")).click();

        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("#primary-menu li.first")).click();

        Thread.sleep(2000);
        assertEquals(driver.getCurrentUrl(), "http://toolsqa.com/");
    }

    private void openIFramePracticePage() {

        Actions hover = new Actions(driver);
        hover.moveToElement(driver.findElement(By.cssSelector("#primary-menu > li.menu-item.menu-item-type-custom.menu-item-object-custom.menu-item-has-children.menu-item-17611.has-children"))).perform();
        driver.findElement(By.cssSelector("a[href='http://toolsqa.com/iframe-practice-page/']")).click();

    }
}
