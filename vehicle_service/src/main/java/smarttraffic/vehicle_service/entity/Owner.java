package smarttraffic.vehicle_service.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@DynamicUpdate
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_number")
    @NotNull
    private String idNumber;

    @Column(name = "license_number")
    @NotNull
    private String licenseNumber;

    @Column(name = "firstname")
    @NotNull
    private String firstName;

    @Column(name = "lastname")
    @NotNull
    private String lastName;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OwnerContact> vehicles;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private OwnerContact ownerContact;

    @Column(name = "points")
    private int points;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Set<OwnerContact> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<OwnerContact> vehicleSet) {
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

    public int getRedusedPoint() {
        points--;
        return points;
    }
}
