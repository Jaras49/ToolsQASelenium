package com.toolsqa.tab.iframe.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toolsqa.AbstractTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TabTest extends AbstractTest {

    private static final String TAB_URL = "http://toolsqa.com/automation-practice-switch-windows/";

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        driver.get(TAB_URL);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void browserWindowTest() throws InterruptedException {
        WebElement button = driver.findElement(By.cssSelector("button[onClick='newBrwWin()']"));
        button.click();
        clickSwitchAndAssert();
    }

    @Test
    public void messageWindowTest() {

        WebElement button = driver.findElement(By.cssSelector("button[onClick='newMsgWin()']"));
        button.click();

        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));

        String text = driver.findElement(By.tagName("body")).getText();
        assertEquals(text, "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.");

        driver.close();
        driver.switchTo().window(windowHandles.get(0));

        driver.findElement(By.id("alert")).click();

        boolean alertPresent = true;
        try {
            driver.switchTo().alert();
        } catch (NoAlertPresentException ex) {
            alertPresent = false;
        }
        assertTrue(alertPresent);
    }

    @Test
    public void browserTabTest() throws InterruptedException {

        WebElement button = driver.findElement(By.cssSelector("button[onClick='newBrwTab()']"));
        button.click();
        clickSwitchAndAssert();
    }

    private void clickSwitchAndAssert() throws InterruptedException {
        ArrayList<String> windowsHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowsHandles.get(1));
        driver.manage().window().maximize();

        List<WebElement> menuElements = driver.findElements(By.cssSelector(".navigation #primary-menu .menu-item"));
        WebElement menuElement = menuElements.get(1).findElement(By.cssSelector("a"));

        Actions hover = new Actions(driver);
        hover.moveToElement(menuElement).perform();
        driver.findElement(By.cssSelector("a[href='http://toolsqa.com/software-testing-tutorial/']")).click();

        Thread.sleep(1000);
        assertEquals(driver.getCurrentUrl(), "http://toolsqa.com/software-testing-tutorial/");

        driver.close();
        driver.switchTo().window(windowsHandles.get(0));
        driver.manage().window().maximize();
        assertEquals(driver.getCurrentUrl(), "http://toolsqa.com/automation-practice-switch-windows/");
    }
}
