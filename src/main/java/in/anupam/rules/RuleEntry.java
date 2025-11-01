package in.anupam.rules;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.anupam.models.Department;

public class RuleEntry {
        public int level;
        @JsonProperty("Rule")
        public InnerRule rule;

        @JsonProperty("Department")
        public Department department;

        public static class InnerRule {
            public String operator;
            public String attrib;
            public double val;
        }


}
