package com.toolsqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.toolsqa.model.table.TableRow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class AutomationFormTestSuite extends AbstractTest {

    private static final String DUMMY_PHOTO_NAME = "form/dummyPhoto.jpg";
    private static final String FORM_URL = "http://toolsqa.com/automation-practice-form/";
    private static final String TABLE_URL = "http://toolsqa.com/automation-practice-table/";

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
