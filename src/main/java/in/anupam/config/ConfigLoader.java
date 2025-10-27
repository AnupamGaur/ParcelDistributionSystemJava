package in.anupam.config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.anupam.rules.RuleEntry;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ConfigLoader {
//    private final ObjectMapper objectMapper;
//    public ConfigLoader() {
//        this.objectMapper = new ObjectMapper();
//    }
//
//    public ProcessingConfiguration loadConfiguration(String configPath) throws IOException {
//        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configPath)) {
//            if (inputStream == null) {
//                throw new IOException("Configuration file not found: " + configPath);
//            }
//            return objectMapper.readValue(inputStream, ProcessingConfiguration.class);
//        }
//    }
//
private static final ObjectMapper MAPPER = new ObjectMapper();
    public static List<RuleEntry> loadRules(String filepath) {
        try (InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(filepath)) {
            if (is == null) {
                throw new IllegalStateException("config.json not found on classpath");
            }
//            System.out.println(is+" This is 'is' of type InputStream ");
            return MAPPER.readValue(is, new TypeReference<List<RuleEntry>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.json", e);
        }
    }

}
