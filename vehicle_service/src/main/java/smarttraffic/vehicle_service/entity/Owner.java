package smarttraffic.vehicle_service.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @OneToMany(mappedBy = "owner")
    private Set<Vehicle> vehicles;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private OwnerContact ownerContact;

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicleSet) {
        this.vehicles = vehicleSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OwnerContact getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(OwnerContact ownerContact) {
        this.ownerContact = ownerContact;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
