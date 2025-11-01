import in.anupam.models.Parcel;
import in.anupam.processors.ParcelProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParcelProcessorRetrieveFieldTest {
    Parcel parcel = new Parcel();
    @BeforeEach
    void setup(){
        parcel.setWeight(11.93);
        parcel.setValue(1001.78);
    }

    @Test
    @DisplayName("Testing if weight attrib fetched correctly")
    void weight_resolveNumeric(){
        Object actualWeight = ParcelProcessor.resolveNumeric(parcel,"weight");
        assertEquals(11.93,actualWeight);
    }

    @Test
    @DisplayName("Testing if value attrib fetched correctly")
    void value_resolveNumeric(){
        Object actualValue = ParcelProcessor.resolveNumeric(parcel,"value");
        assertEquals(1001.78,actualValue);
    }

    @Test
    @DisplayName("for attrib does not exists")
    void size_resolveNumeric(){
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
        {
            ParcelProcessor.resolveNumeric(parcel, "size");
        });
    }

}
