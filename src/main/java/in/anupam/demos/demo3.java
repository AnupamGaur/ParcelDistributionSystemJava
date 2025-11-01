package in.anupam.demos;

import in.anupam.models.Parcel;

import java.io.FileNotFoundException;
import java.util.List;

import static in.anupam.demos.demo1.SEPARATOR;
import static in.anupam.demos.demo2.createTestParcel;
import static in.anupam.demos.demo2.getParcelFlow;

public class demo3 {
    public static void runDemo() throws FileNotFoundException {

        System.out.println("New Department Addition");
        System.out.println(SEPARATOR);
        System.out.println("Added 'UltraHeavy' department for parcels > 100kg");
        System.out.println();

        // Create test parcel with 25kg weight
        Parcel ultraHeavyParcel = createTestParcel("Enterprise Customer", 110.0, 5000.0);

        System.out.println("Test Parcel:");
        System.out.println("   Weight: 110.0 kg");
        System.out.println("   Value: 5000.0");
        System.out.println();

        // Test with config2 (no UltraHeavy)
        System.out.println("Without UltraHeavy Dept (config2.json):");
        List<String> beforeFlow = getParcelFlow(ultraHeavyParcel, "config2.json");
        System.out.println("   Route: " + String.join(" → ", beforeFlow));
        System.out.println("   Result: Goes to HEAVY (default for >10kg)");
        System.out.println();

        // Test with config3 (with UltraHeavy)
        System.out.println(" With UltraHeavy Dept (config3.json):");
        List<String> afterFlow = getParcelFlow(ultraHeavyParcel, "config3.json");
        System.out.println("   Route: " + String.join(" → ", afterFlow));
        System.out.println("   Result: Goes to ULTRAHEAVY (>100kg rule)");
        System.out.println();

        System.out.println(" New department successfully handles ultra-heavy parcels!");
        System.out.println(SEPARATOR);
    }
    }
