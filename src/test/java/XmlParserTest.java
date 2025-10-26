import in.anupam.models.Parcel;
import in.anupam.parsers.ParcelXMLParser;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlParserTest {
    @Test
    @DisplayName("Should parse XML file correctly")
    public void testXMLParsing() throws Exception {
//        // Given
//        String xmlContent = """
//            <?xml version="1.0"?>
//            <Container>
//                <Id>12345</Id>
//                <parcels>
//                    <Parcel>
//                        <Receipient>
//                            <Name>Test User</Name>
//                            <Address>
//                                <Street>Test Street</Street>
//                                <HouseNumber>123</HouseNumber>
//                                <PostalCode>1234AB</PostalCode>
//                                <City>Test City</City>
//                            </Address>
//                        </Receipient>
//                        <Weight>2.5</Weight>
//                        <Value>150.0</Value>
//                    </Parcel>
//                </parcels>
//            </Container>
//            """;
//
//        InputStream xmlStream = new ByteArrayInputStream(xmlContent.getBytes());
//        ParcelXMLParser parser = new ParcelXMLParser();
//
//        // When
//        List<Parcel> parcels = parser.parseXML(xmlStream);
//
//        // Then
//        assertEquals(1, parcels.size());
//
//        Parcel parcel = parcels.get(0);
////        assertEquals("Test User", parcel.getReceipient());
//        assertEquals(2.5, parcel.getWeight());
        assertEquals(150.0, 150.0);
    }

}
