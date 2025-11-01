import in.anupam.models.Parcel;
import in.anupam.processors.ParcelProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelProcessorCompareTest {
    Parcel parcel = new Parcel();
    @Test
    @DisplayName("for attrib does not exists")
    void compare(){
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
        {
            ParcelProcessor.resolveNumeric(parcel, "size");
        });
    }
    @Test
    @DisplayName("Test unsupported operator throws exception")
    void testUnsupportedOperator() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ParcelProcessor.compare(5.0, "===", 10.0);
        });
        assertTrue(exception.getMessage().contains("Unsupported operator"));
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, <, 10.0, true",
            "10.0, <, 5.0, false",
            "5.0, <=, 10.0, true",
            "5.0, <=, 5.0, true",
            "10.0, <=, 5.0, false",
            "10.0, >, 5.0, true",
            "5.0, >, 10.0, false",
            "10.0, >=, 5.0, true",
            "5.0, >=, 5.0, true",
            "5.0, >=, 10.0, false",
            "5.0, ==, 5.0, true",
            "5.0, ==, 10.0, false",
            "5.0, !=, 10.0, true",
            "5.0, !=, 5.0, false"
    })
    @DisplayName("Parameterized test for all operators")
    void testAllOperators(double left, String op, double right, boolean expected) {
        assertEquals(expected, ParcelProcessor.compare(left, op, right));
    }
}
