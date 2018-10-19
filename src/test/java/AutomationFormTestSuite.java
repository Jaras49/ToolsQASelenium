import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import table.TableRow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AutomationFormTestSuite extends AbstractTest {

    private static final String FORM_URL = "http://toolsqa.com/automation-practice-form/";
    private static final String TABLE_URL = "http://toolsqa.com/automation-practice-table/";

    @Test
    public void formTest() {
        driver.get(FORM_URL);

        WebElement firstNameElement = driver.findElement(By.name("firstname"));
        firstNameElement.sendKeys("Jarosław");
        assertEquals(firstNameElement.getAttribute("value"), "Jarosław");


        WebElement lastNameElement = driver.findElement(By.name("lastname"));
        lastNameElement.click();
        lastNameElement.sendKeys("Godwin");
        assertEquals(lastNameElement.getAttribute("value"), "Godwin");

        List<WebElement> sex = driver.findElements(By.name("sex"));
        for (WebElement webElement : sex) {
            if (webElement.getAttribute("value").equalsIgnoreCase("male")) {
                webElement.click();
            }
        }
        assertTrue(driver.findElement(By.id("sex-0")).isSelected());
        assertFalse(driver.findElement(By.id("sex-1")).isSelected());

        WebElement experience = driver.findElement(By.cssSelector(".control-group #exp-0"));
        experience.click();
        assertTrue(driver.findElement(By.cssSelector(".control-group input[value='1']")).isSelected());

        WebElement date = driver.findElement(By.xpath("//p[contains(text(), 'Date')]/input"));
        date.sendKeys("1992-08-01");
        assertEquals(driver.findElement(By.xpath("//input[@id='datepicker']")).getAttribute("value"), "1992-08-01");

        WebElement profession = driver.findElement(By.cssSelector("div.control-group input[value='Automation Tester']"));
        profession.click();
        assertTrue(driver.findElement(By.cssSelector("input[value='Automation Tester']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[value='Manual Tester']")).isSelected());

        WebElement fileInput = driver.findElement(By.className("input-file"));
        fileInput.sendKeys("C:\\Users\\jgodwin\\Desktop");
        assertEquals(fileInput.getAttribute("value").replaceAll(".*\\\\", ""), "Desktop");

        WebElement automationTool = driver.findElement(By.id("tool-2"));
        automationTool.click();
        assertTrue(driver.findElement(By.cssSelector("div.control-group #tool-2")).isSelected());

        Select continents = new Select(driver.findElement(By.id("continents")));
        continents.selectByIndex(1);
        assertTrue(continents.getFirstSelectedOption().isSelected());
        assertEquals(continents.getFirstSelectedOption().getText(), "Europe");

        Select seleniumCommands = new Select(driver.findElement(By.xpath("//div[@class='controls']/select[@id='selenium_commands']")));
        seleniumCommands.selectByVisibleText("Wait Commands");
        assertEquals(seleniumCommands.getFirstSelectedOption().getText(), "Wait Commands");

        WebElement submit = driver.findElement(By.cssSelector("button[id='submit']"));
        submit.click();
    }

    @Test
    public void tableTest() {
        driver.get(TABLE_URL);

        int i = ThreadLocalRandom.current().nextInt(400, 900);
        System.out.println(i);

        WebElement tableContent = driver.findElement(By.cssSelector("div#content.content table"));

        System.out.println(tableContent.findElement(By.cssSelector("thead tr")).getText());

        List<WebElement> tableRows = tableContent.findElements(By.cssSelector("tbody tr"));

        tableRows.stream()
                .min(Comparator.comparingInt(row -> {
                    String heightText = row.findElement(By.cssSelector("td:nth-child(4)")).getText().replaceAll("m", "");
                    return Math.abs(Integer.valueOf(heightText) - i);
                }))
                .ifPresent(result -> System.out.println(result.getText()));
    }


    @Test
    public void tableTest2() {
        driver.get(TABLE_URL);

        int i = ThreadLocalRandom.current().nextInt(400, 800);
        System.out.println(i);

        WebElement tableContent = driver.findElement(By.cssSelector("div#content table"));
        List<WebElement> tableRows = tableContent.findElements(By.cssSelector("tbody tr"));

        List<TableRow> table = tableRows.stream()
                .map(row ->
                        new TableRow(
                                row.findElement(By.cssSelector("th")).getText(),
                                row.findElement(By.cssSelector("td:nth-child(2)")).getText(),
                                row.findElement(By.cssSelector("td:nth-child(3)")).getText(),
                                row.findElement(By.cssSelector("td:nth-child(4)")).getText(),
                                row.findElement(By.cssSelector("td:nth-child(5)")).getText(),
                                row.findElement(By.cssSelector("td:nth-child(6)")).getText())
                ).collect(Collectors.toList());

        List<String> expected = Arrays.asList("Burj Khalifa", "Clock Tower Hotel", "Taipei 101", "Financial Center");

        List<String> names = table.stream()
                .map(TableRow::getConstruction)
                .collect(Collectors.toList());

        assertTrue(names.containsAll(expected));

        table.stream()
                .filter(tableRow -> Integer.valueOf(tableRow.getHeight().replaceAll("m", "")) > i)
                .forEach(System.out::println);
    }
}
