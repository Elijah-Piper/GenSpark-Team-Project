import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

}