import in.anupam.config.ConfigLoader;
import in.anupam.models.Container;
import in.anupam.models.Parcel;
import in.anupam.parsers.ParcelXMLParser;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.time.OffsetDateTime;
import java.util.List;

import static in.anupam.parsers.ParcelXMLParser.parseXml;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class XmlParserTest {
    private Container container;

    @BeforeEach
    void setUp() throws JAXBException, FileNotFoundException {
        container = parseXml("Container_68465468.xml");
    }

    @Test
    @DisplayName("Should parse container ID correctly")
    void containerId_isCorrect() {
        assertEquals("68465468", container.getId());
    }

    @Test
    @DisplayName("Should parse shipping date correctly")
    void shippingDate_isCorrect() {
        OffsetDateTime expectedDate = OffsetDateTime.parse("2016-07-22T00:00:00+02:00");
        assertEquals(expectedDate, container.getShippingDate());
    }

    @Test
    @DisplayName("Should parse the correct number of parcels")
    void parcelCount_isCorrect() {
        assertEquals(17, container.getParcels().size());
    }

    @Test
    @DisplayName("First parcel recipient name should be correct")
    void firstParcelRecipientName_isCorrect() {
        assertEquals("Vinny Gankema", container.getParcels().get(0).getReceipient().getName());
    }

    @Test
    @DisplayName("First parcel recipient address street should be correct")
    void firstParcelRecipientAddressStreet_isCorrect() {
        assertEquals("Marijkestraat", container.getParcels().get(0).getReceipient().getAddress().getStreet());
    }

    @Test
    @DisplayName("First parcel weight should be 0.02")
    void firstParcelWeight_isCorrect() {
        assertEquals(0.02, container.getParcels().get(0).getWeight());
    }

    @Test
    @DisplayName("First parcel value should be 0.0")
    void firstParcelValue_isCorrect() {
        assertEquals(0.0, container.getParcels().get(0).getValue());
    }

    @Test
    @DisplayName("Scenario if file does not exists")
    void file_notExists() {
        assertThrows(UncheckedIOException.class, () -> {
            parseXml("random.xml");
        });
    }
}
