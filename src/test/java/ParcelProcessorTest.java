import in.anupam.models.Address;
import in.anupam.models.Container;
import in.anupam.models.Parcel;
import in.anupam.models.Receipient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import in.anupam.processors.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelProcessorTest {
//    getPipelineFlow
//    Parcel parcel = new Parcel(Re)
    Parcel parcel = new Parcel();
    ParcelProcessor processor = new ParcelProcessor();

    @BeforeEach
    void setup(){
        parcel.setWeight(11.93);
        parcel.setValue(1001.78);
    }

    @Test
    @DisplayName("High value heavy parcel goes to Insurance and Heavy")
    void testGetPipelineFlow_HighValueHeavy() {
        parcel.setValue(2000.0);
        parcel.setWeight(15.0);

        ArrayList<String> flow = processor.getPipelineFlow(parcel);

        assertEquals(Arrays.asList("Insurance", "Heavy"), flow);
    }

    @Test
    @DisplayName("Low value light parcel goes to Mail only")
    void testGetPipelineFlow_LowValueMail() {
        parcel.setValue(500.0);
        parcel.setWeight(0.5);

        ArrayList<String> flow = processor.getPipelineFlow(parcel);

        assertEquals(Arrays.asList("Mail"), flow);
    }

    @Test
    @DisplayName("Boundary: value exactly 1000 does NOT go to Insurance")
    void testGetPipelineFlow_BoundaryValue1000() {
        parcel.setValue(1000.0);
        parcel.setWeight(5.0);

        ArrayList<String> flow = processor.getPipelineFlow(parcel);

        assertEquals(Arrays.asList("Regular"), flow);

        }
    @Test
    @DisplayName("Boundary: weight exactly 1 goes to Mail")
    void testGetPipelineFlow_BoundaryWeight1() {
        parcel.setValue(500.0);
        parcel.setWeight(1.0);

        ArrayList<String> flow = processor.getPipelineFlow(parcel);

        assertEquals(Arrays.asList("Mail"), flow);
    }
    @Test
    @DisplayName("Boundary: weight exactly 10 goes to Regular")
    void testGetPipelineFlow_BoundaryWeight10() {
        parcel.setValue(500.0);
        parcel.setWeight(10.0);

        ArrayList<String> flow = processor.getPipelineFlow(parcel);

        assertEquals(Arrays.asList("Regular"), flow);
    }




}
