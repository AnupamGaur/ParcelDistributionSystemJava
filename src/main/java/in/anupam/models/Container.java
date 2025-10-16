package in.anupam.models;

import jakarta.xml.bind.annotation.*;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;

@XmlRootElement(name = "Container")
@XmlAccessorType(XmlAccessType.FIELD)
public class Container {
    @XmlElementWrapper(name = "Id")
    private String id;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime shippingDate;

    @XmlElementWrapper(name = "parcels")
    @XmlElement(name="Parcel")
    private ArrayList<Parcel> parcels = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        shippingDate = shippingDate;
    }

    public ArrayList<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(ArrayList<Parcel> parcels) {
        this.parcels = parcels;
    }
}
