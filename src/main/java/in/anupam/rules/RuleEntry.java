package in.anupam.rules;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RuleEntry {
        public int level;
        @JsonProperty("Rule")
        public InnerRule rule;

        @JsonProperty("Department")
        public String department;

        public static class InnerRule {
            public String operator;
            public String attrib;
            public double val;
        }


}
