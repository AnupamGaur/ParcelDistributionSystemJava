package in.anupam.parsers;

import in.anupam.models.Container;
import in.anupam.models.Parcel;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParcelXMLParser {
    public static Container parseXml(String filename) throws JAXBException, FileNotFoundException
    {
        JAXBContext context = JAXBContext.newInstance(Container.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Load from classpath (resources folder)
        InputStream inputStream = ParcelXMLParser.class.getClassLoader()
                .getResourceAsStream(filename);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources: " + filename);
        }

        try(inputStream) {
            return (Container) unmarshaller.unmarshal(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
