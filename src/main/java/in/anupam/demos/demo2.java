package in.anupam.demos;

import in.anupam.config.ConfigLoader;
import in.anupam.models.Parcel;
import in.anupam.models.Receipient;
import in.anupam.processors.ParcelProcessor;
import in.anupam.rules.RuleEntry;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static in.anupam.demos.demo1.SEPARATOR;

public class demo2 {

    public static void runDemo() throws FileNotFoundException{

        System.out.println("DEMO 2: Rule Modification Impact");
        System.out.println(SEPARATOR);
        System.out.println("Regular dept threshold changed from 10kg → 11kg");
        System.out.println();

        // Create test parcel with 10.5kg weight
        Parcel testParcel = createTestParcel("Test Customer", 10.5, 100.0);

        System.out.println("Test Parcel:");
        System.out.println("   Weight: 10.5 kg");
        System.out.println("   Value: $100.00");
        System.out.println();

        // Test with original config
        System.out.println(" With Original Rules (config.json):");
        List<String> originalFlow = getParcelFlow(testParcel, "config.json");
        System.out.println("   Route: " + String.join(" → ", originalFlow));
        System.out.println();

        // Test with modified config
        System.out.println(" With Modified Rules (config2.json):");
        List<String> modifiedFlow = getParcelFlow(testParcel, "config2.json");
        System.out.println("   Route: " + String.join(" → ", modifiedFlow));
        System.out.println();

        System.out.println("Rule change successfully redirected parcel!");
        System.out.println(SEPARATOR);
    }
    public static Parcel createTestParcel(String recipientName,
                                           double weight, double value) {
        Parcel parcel = new Parcel();
        parcel.setWeight(weight);
        parcel.setValue(value);

        Receipient recipient = new Receipient();
        recipient.setName(recipientName);
        parcel.setReceipient(recipient);

        return parcel;
    }

    public static List<String> getParcelFlow(Parcel parcel, String configFile)
            throws FileNotFoundException {

        List<RuleEntry> rules = ConfigLoader.loadRules(configFile);
        ParcelProcessor processor = new ParcelProcessor();

        return processor.getPipelineFlow(parcel,configFile)
                .stream()
                .map(dep -> dep.getName())
                .collect(Collectors.toList());
    }
}
