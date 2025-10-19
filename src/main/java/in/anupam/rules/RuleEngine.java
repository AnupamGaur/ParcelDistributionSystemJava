package in.anupam.rules;

import java.util.HashMap;
import java.util.Map;

public class RuleEngine {
    private final Map<String, RuleEvaluator> evaluators;

    public RuleEngine() {
        this.evaluators = new HashMap<>();
        registerDefaultEvaluators();
    }

    private void registerDefaultEvaluators() {
        registerEvaluator(new WeightRuleEvaluator());
        registerEvaluator(new ValueEvaluator());
    }
    public void registerEvaluator(RuleEvaluator evaluator) {
        evaluators.put(evaluator.getType(), evaluator);
    }
}
