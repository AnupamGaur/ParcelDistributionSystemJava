package in.anupam;

import in.anupam.config.ConfigLoader;
import in.anupam.models.Container;

import in.anupam.rules.RuleEntry;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import static in.anupam.parsers.ParcelXMLParser.parseXml;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
    Container container = parseXml("Container_68465468.xml");
        System.out.println(container.getId());
        System.out.println(container.getShippingDate());
        System.out.println(container.getParcels());
        System.out.println(container.getParcels().get(2).getValue());
        final List<RuleEntry> RULES = ConfigLoader.loadRules();
        System.out.println(RULES.get(0).department);
    }
}