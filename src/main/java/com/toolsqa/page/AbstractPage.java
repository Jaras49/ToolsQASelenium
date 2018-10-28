package com.toolsqa.page;

import com.toolsqa.annotation.WaitUntilVisible;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    protected AbstractPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
    }

    protected WebElement waitUntilClickable(WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected List<WebElement> waitUntilVisible(List<WebElement> webElements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    protected WebElement waitUntilVisible(WebElement webElement) {
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected Alert waitUntilAlertPresent() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void switchToNewWindow(int index) {
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(index));
        driver.manage().window().maximize();
    }

    public <T, M> T assertEquals(M actual, M expected, T pageObject) {
        Assert.assertEquals(actual, expected);
        return pageObject;
    }

    protected void waitUntilPageLoads() {
        wait.until(ExpectedConditions.visibilityOfAllElements(getFieldsAnnotatedWithWaitForElement()));
    }

    private List<WebElement> getFieldsAnnotatedWithWaitForElement() {
        List<WebElement> elements = new ArrayList<>();
        try {
            for (Field declaredField : this.getClass().getDeclaredFields()) {
                declaredField.setAccessible(true);
                if (declaredField.getType() == WebElement.class && declaredField.isAnnotationPresent(WaitUntilVisible.class)) {
                    elements.add(((WebElement) declaredField.get(this)));
                }
                declaredField.setAccessible(false);
            }

        } catch (IllegalAccessException ex) {
            System.err.println(ex);
        }
        return elements;
    }
}
