package com.toolsqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {

    private static final String CHROME_DRIVER_NAME = "chromedriver.exe";
    private static final String TOOLS_QA_URL = "http://toolsqa.com/";

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String driverPath = this.getClass().getClassLoader().getResource(CHROME_DRIVER_NAME).getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.get(TOOLS_QA_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
