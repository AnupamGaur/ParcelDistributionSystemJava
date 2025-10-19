package in.anupam.rules;

import in.anupam.config.ProcessingConfiguration;
import in.anupam.models.Parcel;

public class ValueEvaluator implements RuleEvaluator{
    @Override
    public boolean evaluate(Parcel parcel, ProcessingConfiguration.Rule rule) {
        return false;
    }

    @Override
    public String getType() {
        return "VALUE";
    }
}
