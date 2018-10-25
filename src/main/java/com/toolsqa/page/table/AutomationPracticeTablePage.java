package com.toolsqa.page.table;

import com.toolsqa.model.table.TableRow;
import com.toolsqa.page.AbstractPage;
import com.toolsqa.page.menu.MenuPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AutomationPracticeTablePage extends AbstractPage {

    private MenuPage menu;

    @FindBy(css = "div#content table")
    private WebElement table;

    @FindBy(css = "div#content table thead tr")
    private WebElement tableHeaderRow;

    @FindBy(css = "div#content table tbody tr")
    private List<WebElement> tableRows;

    public AutomationPracticeTablePage(WebDriver driver, WebDriverWait wait, Actions actions, MenuPage menu) {
        super(driver, wait, actions);
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public String getTableHeaderText() {
        return waitUntilVisible(tableHeaderRow).getText();
    }

    public String findRowWithHeightClosestToValue(int value) {
        return waitUntilVisible(tableRows).stream()
                .min(Comparator.comparingInt(row -> {
                    String heightText = row.findElement(By.cssSelector("td:nth-child(4)")).getText().replaceAll("m", "");
                    return Math.abs(Integer.valueOf(heightText) - value);
                }))
                .map(WebElement::getText)
                .orElse("");
    }

    public List<TableRow> mapTableContentToList() {
        return waitUntilVisible(tableRows).stream()
                .map(row ->
                        new TableRow(
                                row.findElement(By.cssSelector("th")).getText(),
                                row.findElement(By.cssSelector("td:nth-child(2)")).getText(),
                                row.findElement(By.cssSelector("td:nth-child(3)")).getText(),
                                row.findElement(By.cssSelector("td:nth-child(4)")).getText(),
                                row.findElement(By.cssSelector("td:nth-child(5)")).getText(),
                                row.findElement(By.cssSelector("td:nth-child(6)")).getText())
                ).collect(Collectors.toList());
    }

    public MenuPage getMenu() {
        return menu;
    }
}
