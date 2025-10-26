package in.anupam.processors;

import in.anupam.config.ConfigLoader;
import in.anupam.config.ProcessingConfiguration;
import in.anupam.models.Parcel;
import in.anupam.models.ProcessingResult;
import in.anupam.rules.RuleEngine;
import in.anupam.rules.RuleEntry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ParcelProcessor {
    public ArrayList<String> getPipelineFlow(Parcel parcel) {
//        System.out.println(RULES+ "\nRULES");
        final List<RuleEntry> RULES = ConfigLoader.loadRules();
        ArrayList<String> flow = new ArrayList<>();

        // Group by level, in ascending order (1, 2, 3, …)
        Map<Integer, List<RuleEntry>> byLevel = RULES.stream()
                .collect(Collectors.groupingBy(r -> r.level, TreeMap::new, Collectors.toList()));

        for (Map.Entry<Integer, List<RuleEntry>> levelEntry : byLevel.entrySet()) {
            boolean matchedAtThisLevel = false;

            for (RuleEntry re : levelEntry.getValue()) {
                double left = resolveNumeric(parcel, re.rule.attrib);
                double right = re.rule.threshold;

                if (compare(left, re.rule.operator, right)) {
                    flow.add(re.department);
                    matchedAtThisLevel = true;
                    // mimic else-if within the same level: stop after first match
                    break;
                }
            }

            // If you want a default per-level, you could add a special rule in JSON
            // (e.g., operator: "default") or handle it here if (!matchedAtThisLevel) { ... }
        }
        return flow;
    }
    private static boolean compare(double left, String op, double right) {
        switch (op) {
            case "<":  return left < right;
            case "<=": return left <= right;
            case ">":  return left > right;
            case ">=": return left >= right;
            case "==": return left == right;
            case "!=": return left != right;
            default:   throw new IllegalArgumentException("Unsupported operator: " + op);
        }
    }

    private static double resolveNumeric(Object target, String attrib) {
        try {
            // Try getter first: getValue(), getWeight(), etc.
            String getter = "get" + Character.toUpperCase(attrib.charAt(0)) + attrib.substring(1);
            Method m = target.getClass().getMethod(getter);
            Object v = m.invoke(target);
            return ((Number) v).doubleValue();
        } catch (NoSuchMethodException e) {
            try {
                // Fallback to direct field access: value, weight
                Field f = target.getClass().getDeclaredField(attrib);
                f.setAccessible(true);
                Object v = f.get(target);
                return ((Number) v).doubleValue();
            } catch (Exception ex) {
                throw new RuntimeException("Cannot resolve attribute: " + attrib, ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading attribute: " + attrib, e);
        }
    }

}
