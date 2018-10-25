package com.toolsqa.table;

import com.toolsqa.AbstractTest;
import com.toolsqa.factory.PageObjectFactory;
import com.toolsqa.model.table.TableRow;
import com.toolsqa.page.table.AutomationPracticeTablePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class AutomationPracticeTableTest extends AbstractTest {

    private AutomationPracticeTablePage automationPracticeTablePage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        automationPracticeTablePage = PageObjectFactory.createAutomationPracticeTable(driver);
        openAutomationPracticeTablePage();
    }

    @Test
    public void shouldFindBuildingWithHeightClosestToRandomValue() {
        int random = ThreadLocalRandom.current().nextInt(400, 800);
        System.out.println("Random height " + random);

        System.out.println(automationPracticeTablePage.getTableHeaderText());
        System.out.println(automationPracticeTablePage.findRowWithHeightClosestToValue(random));
    }

    @Test
    public void shouldMapTableToObjectsAndFindHigherThanRandom() {
        int random = ThreadLocalRandom.current().nextInt(400, 800);
        System.out.println("Random height " + random);

        List<TableRow> tableRows = automationPracticeTablePage.mapTableContentToList();

        List<String> expected = Arrays.asList("Burj Khalifa", "Clock Tower Hotel", "Taipei 101", "Financial Center");
        List<String> names = tableRows.stream()
                .map(TableRow::getConstruction)
                .collect(Collectors.toList());
        assertTrue(names.containsAll(expected));

        tableRows.stream()
                .filter(tableRow -> Integer.valueOf(tableRow.getHeight().replaceAll("m", "")) > random)
                .forEach(System.out::println);
    }

    private void openAutomationPracticeTablePage() {
        automationPracticeTablePage.getMenu().moveToDemoSites();
        automationPracticeTablePage.getMenu().goToAutomationPracticeTablePage();
    }
}
