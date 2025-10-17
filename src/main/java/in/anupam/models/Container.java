package in.anupam.models;

import jakarta.xml.bind.annotation.*;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@XmlRootElement(name = "Container")
@XmlAccessorType(XmlAccessType.FIELD)
public class Container {
    @XmlElement(name = "Id")
    private String id;
    @XmlElement(name = "ShippingDate")
    @XmlJavaTypeAdapter(OffsetDateTimeAdapter.class)
    private OffsetDateTime shippingDate;

    @XmlElementWrapper(name = "parcels")
    @XmlElement(name="Parcel")
    private ArrayList<Parcel> parcels = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OffsetDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(OffsetDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public ArrayList<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(ArrayList<Parcel> parcels) {
        this.parcels = parcels;
    }
}
