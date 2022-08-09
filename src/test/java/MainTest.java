import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "CF", "cf", "column", "columns", "format", "formatter", "formatting", "columnformatting", "column formatting"})
    void determineUtilityAsColumnFormatter(String input) {
        // determineUtility should return '1' if it's determined ColumnFormatter is requested
        assertEquals(1, Main.determineUtil(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "S", "s", "schedule", "scheduler", "scheduling"})
    void determineUtilityAsScheduler(String input) {
        // determineUtility should return '2' if it's determined Scheduler is requested
        assertEquals(2, Main.determineUtil(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12", "scf", "cfs", "colum", "schedu"})
    void determineUtilityAsUnknown(String input) {
        // determineUtility should return '0' if no utility is able to be determined based on the input
        assertEquals(0, Main.determineUtil(input));
    }

    @Test
    void readInputFileDoesNotExist() {
        // If file does not exist, an empty ArrayList should be returned
        List<String> emptyList = new ArrayList<String>();
        assertEquals(emptyList, Main.readInputFile("src/test/resources/SchedulerTestInput/thisFileDoesNotExist.txt"));
    }

    @Test
    void readInputFileCFFile() {
        // This test input file contains number words One-Seven. An ArrayList containing each word should be returned.
        List<String> actual = Main.readInputFile("src/test/resources/ColumnFormatterTestInput/input1.txt");
        List<String> expected = new ArrayList<String>(Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven"));
        assertEquals(expected, actual);

    }

}