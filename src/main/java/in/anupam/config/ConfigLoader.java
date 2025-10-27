package in.anupam.config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.anupam.rules.RuleEntry;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ConfigLoader {
private static final ObjectMapper MAPPER = new ObjectMapper();
    public static List<RuleEntry> loadRules(String filepath) {
        try (InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(filepath)) {
            if (is == null) {
                throw new IllegalStateException("config.json not found on classpath");
            }
            return MAPPER.readValue(is, new TypeReference<List<RuleEntry>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.json", e);
        }
    }

}
