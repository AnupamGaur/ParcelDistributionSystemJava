package in.anupam.models;

import java.util.ArrayList;

public class RoutingResult {
    public Parcel parcel;
    public ArrayList<String> departments = new ArrayList<>();
    public String routingReason;


    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public ArrayList<String> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<String> departments) {
        this.departments = departments;
    }

    public String getRoutingReason() {
        return routingReason;
    }

    public void setRoutingReason(String routingReason) {
        this.routingReason = routingReason;
    }
}
