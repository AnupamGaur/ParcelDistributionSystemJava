package in.anupam.processors;

import in.anupam.config.ProcessingConfiguration;
import in.anupam.models.Parcel;
import in.anupam.models.ProcessingResult;
import in.anupam.rules.RuleEngine;

public class ParcelProcessor {
    private final RuleEngine ruleEngine;
    private final ProcessingConfiguration configuration;

    public ParcelProcessor(RuleEngine ruleEngine, ProcessingConfiguration configuration) {
        this.ruleEngine = ruleEngine;
        this.configuration = configuration;
    }
    public ProcessingResult processParcel(Parcel parcel) {

    }
}
