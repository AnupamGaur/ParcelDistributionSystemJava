import in.anupam.config.ConfigLoader;
import in.anupam.rules.RuleEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigLoaderTest {
    private List<RuleEntry> rules;
    @BeforeEach
    void setup(){
        rules = ConfigLoader.loadRules("config.json");
    }

    @Test
    @DisplayName("Scenario if file does not exists")
    void file_notExists() {
        assertThrows(IllegalStateException.class, () -> {
            ConfigLoader.loadRules("random.json");
        });

    }
}
