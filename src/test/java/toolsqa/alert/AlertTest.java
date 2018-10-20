package toolsqa.alert;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import toolsqa.AbstractTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AlertTest extends AbstractTest {

    private static final String ALERT_URL = "http://toolsqa.com/handling-alerts-using-selenium-webdriver/";

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        driver.get(ALERT_URL);
    }

    @Test
    public void simpleAlertTest() {
        boolean present = true;
        try {
            driver.findElement(By.cssSelector("button[onClick='pushAlert()']")).click();
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException ex) {
            present = false;
        } finally {
            assertTrue(present);
        }
    }

    @Test
    public void confirmAlertTest() {
        WebElement button = driver.findElement(By.cssSelector("button[onClick='pushConfirm()']"));
        WebElement confirmText = driver.findElement(By.cssSelector("#ConfirmOption"));

        button.click();
        driver.switchTo().alert().accept();
        assertEquals(confirmText.getText(), "You have clicked on OK");

        button.click();
        driver.switchTo().alert().dismiss();
        assertEquals(confirmText.getText(), "You have clicked on Cancel");
    }

    @Test
    public void promptAlertTest() {
        WebElement button = driver.findElement(By.cssSelector("button[onClick='promptConfirm()']"));
        button.click();

        Alert alert = driver.switchTo().alert();
        assertEquals(alert.getText(), "Do you like toolsqa?");

        alert.sendKeys("Yes");
        alert.accept();
    }
}
