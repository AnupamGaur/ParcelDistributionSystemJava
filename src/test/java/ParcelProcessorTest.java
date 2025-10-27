import in.anupam.models.Address;
import in.anupam.models.Container;
import in.anupam.models.Parcel;
import in.anupam.models.Receipient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import in.anupam.processors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParcelProcessorTest {
//    getPipelineFlow
//    Parcel parcel = new Parcel(Re)
    Parcel parcel = new Parcel();
    Address addr = new Address();
    Receipient receipient = new Receipient();
    Container container = new Container();

    @BeforeEach
    void setup(){
        addr.setPostalCode("578");
        addr.setHouseNumber("28");
        addr.setStreet("Meester Willemstraat");
        addr.setCity("Hilversum");

        receipient.setAddress(addr);
        receipient.setName("Anupam");

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

    @Test
    @DisplayName("for attrib does not exists")
    void compare(){
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
        {
            ParcelProcessor.resolveNumeric(parcel, "size");
        });
    }


}
