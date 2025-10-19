package in.anupam.rules;

import in.anupam.config.ProcessingConfiguration;
import in.anupam.models.Parcel;

public interface RuleEvaluator {
    boolean evaluate(Parcel parcel, ProcessingConfiguration.Rule rule);
    String getType();
}
