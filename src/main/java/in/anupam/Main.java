package in.anupam;

import in.anupam.config.ConfigLoader;
import in.anupam.models.Container;

import in.anupam.models.Parcel;
import in.anupam.processors.ParcelProcessor;
import in.anupam.rules.RuleEntry;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

import static in.anupam.parsers.ParcelXMLParser.parseXml;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
    Container container = parseXml("Container_68465468.xml");
//        System.out.println(container.getId());
//        System.out.println(container.getShippingDate());
//        System.out.println(container.getParcels());
        System.out.println(container.getParcels().get(0).getReceipient().getName());
        final List<RuleEntry> RULES = ConfigLoader.loadRules("config.json");
//        System.out.println(RULES.get(0).department);
        ParcelProcessor parcelProcessor  = new ParcelProcessor();
        ArrayList<Parcel> parcels = container.getParcels();
        for (Parcel parcel: parcels){
            System.out.println("Value:"+parcel.getValue());
            System.out.println("Weight:"+parcel.getWeight());
            System.out.println(parcelProcessor.getPipelineFlow(parcel));
        }
//        System.out.println(parcelProcessor.getPipelineFlow(parcel));
    }
}