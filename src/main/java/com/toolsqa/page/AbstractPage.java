package com.toolsqa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}
