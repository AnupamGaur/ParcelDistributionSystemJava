package in.anupam.processors;

import in.anupam.config.ConfigLoader;
import in.anupam.models.Department;
import in.anupam.models.Parcel;
import in.anupam.rules.RuleEntry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ParcelProcessor {
    public ArrayList<Department> getPipelineFlow(Object ob, String config) {
        final List<RuleEntry> RULES = ConfigLoader.loadRules(config);
        ArrayList<Department> flow = new ArrayList<>();
        Map<Integer, List<RuleEntry>> byLevel = RULES.stream()
                .collect(Collectors.groupingBy(r -> r.level, TreeMap::new, Collectors.toList()));

        for (Map.Entry<Integer, List<RuleEntry>> levelEntry : byLevel.entrySet()) {
            boolean matchedAtThisLevel = false;

            for (RuleEntry re : levelEntry.getValue()) {
                double left = resolveNumeric(ob, re.rule.attrib);
                double right = re.rule.val;

                if (compare(left, re.rule.operator, right)) {
                    flow.add(re.department);
                    matchedAtThisLevel = true;
                    break;
                }
            }
        }
        return flow;
    }
    public static boolean compare(double left, String op, double right) {
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

    public static double resolveNumeric(Object target, String attrib) {
        try {
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
