import in.anupam.config.ConfigLoader;
import in.anupam.rules.RuleEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigLoaderTest {
    private List<RuleEntry> rules;
    @BeforeEach
    void setup(){
        rules = ConfigLoader.loadRules("config.json");
    }

    @Test
    @DisplayName("Successfully load valid rules from config.json")
    void testLoadRules_ValidFile() {

        assertNotNull(rules);
        assertFalse(rules.isEmpty());

        // Verify structure of first rule (adjust based on your actual data)
        RuleEntry firstRule = rules.get(0);
        assertNotNull(firstRule);
        // Add more specific assertions based on your RuleEntry structure
        // e.g., assertNotNull(firstRule.getCondition());
    }

    @Test
    @DisplayName("Scenario if file does not exists")
    void file_notExists() {
        assertThrows(IllegalStateException.class, () -> {
            ConfigLoader.loadRules("random.json");
        });

    }
}
