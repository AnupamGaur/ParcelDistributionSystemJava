package in.anupam.demos;

import in.anupam.config.ConfigLoader;
import in.anupam.models.Container;
import in.anupam.models.Parcel;
import in.anupam.models.Receipient;
import in.anupam.processors.ParcelProcessor;
import in.anupam.rules.RuleEntry;
import jakarta.xml.bind.JAXBException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static in.anupam.parsers.ParcelXMLParser.parseXml;

public class demo1 {

    public static final String SEPARATOR = "=".repeat(80);
    public static final String LINE = "-".repeat(80);


    public static void runDemo()
            throws JAXBException, FileNotFoundException {

        printHeader("PARCEL PROCESSING SYSTEM DEMO");

        // Step 1: Load Container
        System.out.println("\n Container from XML");
        System.out.println(LINE);
        Container container = parseXml("Container_68465468.xml");
        System.out.println(" Container loaded successfully");
        System.out.println("  Container ID: " + container.getId());
        System.out.println("  Shipping Date: " + container.getShippingDate());
        System.out.println("  Total Parcels: " + container.getParcels().size());


        ParcelProcessor parcelProcessor = new ParcelProcessor();

        System.out.println("\nProcessing Parcels");
        System.out.println(SEPARATOR);

        ArrayList<Parcel> parcels = container.getParcels();
        int parcelNumber = 1;

        for (Parcel parcel : parcels) {
            processAndDisplayParcel(parcel, parcelNumber++, parcelProcessor);
        }


    }

    private static void processAndDisplayParcel(Parcel parcel, int number,
                                                ParcelProcessor processor) {
        System.out.println("\nParcel #" + number);
        System.out.println(LINE);

        // Display parcel details
        Receipient recipient = parcel.getReceipient();
        System.out.println("  Recipient: " + recipient.getName());
        System.out.println("  Address: " + recipient.getAddress().getHouseNumber() + " " +
                recipient.getAddress().getStreet() + " " + recipient.getAddress().getCity() + " " +recipient.getAddress().getPostalCode() );
        System.out.println("  Value: " + String.format("%.2f", parcel.getValue()));
        System.out.println("  Weight: " + String.format("%.2f", parcel.getWeight()) + " kg");

        // Get pipeline flow
        List<String> depNames = processor.getPipelineFlow(parcel,"config.json")
                .stream()
                .map(dep -> dep.getName())
                .collect(Collectors.toList());

        System.out.println("\n Processing Pipeline:");
        for (int i = 0; i < depNames.size(); i++) {
            String arrow = (i < depNames.size() - 1) ? " → " : "";
            System.out.print("     " + (i + 1) + ". " + depNames.get(i) + arrow);
            if (i < depNames.size() - 1) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }


    private static void printHeader(String title) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("  " + title);
        System.out.println(SEPARATOR);
    }

}

