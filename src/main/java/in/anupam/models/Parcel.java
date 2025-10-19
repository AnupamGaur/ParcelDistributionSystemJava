package in.anupam.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class Parcel {
    @XmlElement(name = "Receipient")
    private Receipient receipient;
    private BigDecimal weight;
    private BigDecimal value;

    public Parcel(Receipient receipient, BigDecimal weight, BigDecimal value) {
        this.receipient = receipient;
        this.weight = weight;
        this.value = value;
    }


    public Receipient getReceipient() {
        return receipient;
    }

    public void setReceipient(Receipient receipient) {
        this.receipient = receipient;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
