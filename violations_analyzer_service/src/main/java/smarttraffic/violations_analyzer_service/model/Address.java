package smarttraffic.violations_analyzer_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("address")
public class Address {
    @Id
    private long id;
    private String country;
    private String city;
    private String street;
    private String building;
    private String apartment;
    private int zipCode;
    private Set<OwnerContact> owners;

    public Address(String country, String city, String street, String building, String apartment, int zipCode, Set<OwnerContact> owners) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.zipCode = zipCode;
        this.owners = owners;

    }

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Set<OwnerContact> getOwners() {
        return owners;
    }

    public void setOwners(Set<OwnerContact> owners) {
        this.owners = owners;
    }

}
