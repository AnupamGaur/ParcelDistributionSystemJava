package in.anupam.parsers;

import in.anupam.models.Parcel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParcelXMLParser {
    public List<Parcel> parseXML(InputStream xmlInputStream) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlInputStream);

        List<Parcel> parcels = new ArrayList<>();
        NodeList parcelNodes = document.getElementsByTagName("Parcel");

        for (int i = 0; i < parcelNodes.getLength(); i++) {
            Node parcelNode = parcelNodes.item(i);
            if (parcelNode.getNodeType() == Node.ELEMENT_NODE) {
                Element parcelElement = (Element) parcelNode;
                parcels.add(parseParcel(parcelElement));
            }
        }

        return parcels;
    }

    private Parcel parseParcel(Element parcelElement) {

    }
}
