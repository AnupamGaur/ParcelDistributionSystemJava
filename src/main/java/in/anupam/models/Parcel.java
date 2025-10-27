package in.anupam.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class Parcel {
    @XmlElement(name = "Receipient")
    private Receipient receipient;
    @XmlElement(name = "Weight")
    private double weight;
    @XmlElement(name = "Value")
    private double value;


    public Receipient getReceipient() {
        return receipient;
    }

    public void setReceipient(Receipient receipient) {
        this.receipient = receipient;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
