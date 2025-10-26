package in.anupam.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlElement(name = "Street")
    private String street;
    @XmlElement(name = "HouseNumber")
    private String houseNumber;
    @XmlElement(name = "PostalCode")
    private String postalCode;
    @XmlElement(name = "City")
    private String city;

//    public Address(String street, String houseNumber, String postalCode, String city){
//        this.street = street;
//        this.houseNumber = houseNumber;
//        this.postalCode = postalCode;
//        this.city = city;
//    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

}
