package com.toolsqa;

import com.toolsqa.factory.PageObjectFactory;
import com.toolsqa.page.menu.MenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {

    private static final String CHROME_DRIVER_NAME = "chromedriver.exe";
    private static final String TOOLS_QA_URL = "http://toolsqa.com/";

    protected WebDriver driver;
    protected MenuPage menuPage;

    @BeforeMethod
    public void setUp() {
        String driverPath = this.getClass().getClassLoader().getResource(CHROME_DRIVER_NAME).getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TOOLS_QA_URL);

        menuPage = PageObjectFactory.createMenuPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
