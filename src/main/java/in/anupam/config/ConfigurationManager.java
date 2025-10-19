package in.anupam.config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationManager {
    private final ObjectMapper objectMapper;
    public ConfigurationManager() {
        this.objectMapper = new ObjectMapper();
    }

    public ProcessingConfiguration loadConfiguration(String configPath) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configPath)) {
            if (inputStream == null) {
                throw new IOException("Configuration file not found: " + configPath);
            }
            return objectMapper.readValue(inputStream, ProcessingConfiguration.class);
        }
    }


}
