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

    private static final String SEPARATOR = "=".repeat(80);
    private static final String LINE = "-".repeat(80);

    public static void main(String[] args) {
        try {
            runDemo("Container_68465468.xml", "config.json");
        } catch (Exception e) {
            System.err.println("Error running demo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void runDemo(String xmlFile, String configFile)
            throws JAXBException, FileNotFoundException {

        printHeader("PARCEL PROCESSING SYSTEM DEMO");

        // Step 1: Load Container
        System.out.println("\n Container from XML");
        System.out.println(LINE);
        Container container = parseXml(xmlFile);
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
        List<String> depNames = processor.getPipelineFlow(parcel)
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

    private static void displayRulesSummary(List<RuleEntry> rules) {
        if (rules.isEmpty()) {
            System.out.println("  (No rules configured)");
            return;
        }

        System.out.println("\n  Configured Rules:");
        for (int i = 0; i < Math.min(rules.size(), 5); i++) {
            RuleEntry rule = rules.get(i);
            System.out.println("    " + (i + 1) + ". Department: " + rule.department.getName());
        }
        if (rules.size() > 5) {
            System.out.println("    ... and " + (rules.size() - 5) + " more");
        }
    }

    private static void displaySummary(ArrayList<Parcel> parcels,
                                       ParcelProcessor processor) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("📊 PROCESSING SUMMARY");
        System.out.println(SEPARATOR);

        double totalValue = parcels.stream()
                .mapToDouble(Parcel::getValue)
                .sum();

        double totalWeight = parcels.stream()
                .mapToDouble(Parcel::getWeight)
                .sum();

        System.out.println("\n  Total Parcels Processed: " + parcels.size());
        System.out.println("  Total Value: " + String.format("%.2f", totalValue));
        System.out.println("  Total Weight: " + String.format("%.2f", totalWeight) + " kg");
        System.out.println("  Average Value: " +
                String.format("%.2f", totalValue / parcels.size()));
        System.out.println("  Average Weight: " +
                String.format("%.2f", totalWeight / parcels.size()) + " kg");

        // Department usage statistics
        System.out.println("\n  Department Usage:");
        var deptUsage = new java.util.HashMap<String, Integer>();

        for (Parcel parcel : parcels) {
            List<String> deps = processor.getPipelineFlow(parcel)
                    .stream()
                    .map(dep -> dep.getName())
                    .collect(Collectors.toList());

            for (String dept : deps) {
                deptUsage.put(dept, deptUsage.getOrDefault(dept, 0) + 1);
            }
        }

        deptUsage.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(entry ->
                        System.out.println("    • " + entry.getKey() +
                                ": " + entry.getValue() + " parcel(s)"));
    }

    private static void printHeader(String title) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("  " + title);
        System.out.println(SEPARATOR);
    }

    private static void printFooter() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("✅ Demo completed successfully!");
        System.out.println(SEPARATOR + "\n");
    }
}

