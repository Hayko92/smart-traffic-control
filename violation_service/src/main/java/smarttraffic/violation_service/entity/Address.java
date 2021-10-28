package smarttraffic.violation_service.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vehicle_owner_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private String building;

    @Column(name = "zip_code")
    private int zipCode;

    @OneToMany(mappedBy = "address")
    private Set<Owner> owners;

    public Address() {
    }
//todo add owner relations
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

}
