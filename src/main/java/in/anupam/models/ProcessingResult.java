package in.anupam.models;

import java.util.ArrayList;
import java.util.List;

public class ProcessingResult {
    private final Parcel parcel;
    private final List<String> assignedDepartments;
    private final List<String> processingSteps;

    public ProcessingResult(Parcel parcel) {
        this.parcel = parcel;
        this.assignedDepartments = new ArrayList<>();
        this.processingSteps = new ArrayList<>();
    }

    public void addDepartment(String department) {
        assignedDepartments.add(department);
    }

    public void addProcessingStep(String step){
        processingSteps.add(step);
    }

    public Parcel getParcel() {
        return parcel;
    }

    public List<String> getAssignedDepartments() {
        return assignedDepartments;
    }

    public List<String> getProcessingSteps() {
        return processingSteps;
    }
}
