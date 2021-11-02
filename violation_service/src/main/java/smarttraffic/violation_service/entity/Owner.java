package smarttraffic.violation_service.entity;

import javax.persistence.*;
import java.util.Objects;
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

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Vehicle> vehicles;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "contact_id")
    private OwnerContact ownerContact;

    @Column(name = "points")
    private int points;

    public Owner() {

    }

    public Owner(String idNumber, String licenseNumber, String firstName, String lastName, int points, OwnerContact ownerContact) {
        this.idNumber = idNumber;
        this.licenseNumber = licenseNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
        this.ownerContact = ownerContact;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id && points == owner.points && idNumber.equals(owner.idNumber) && licenseNumber.equals(owner.licenseNumber) && firstName.equals(owner.firstName) && lastName.equals(owner.lastName) && Objects.equals(ownerContact, owner.ownerContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idNumber, licenseNumber, firstName, lastName, points, ownerContact);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", idNumber='" + idNumber + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", points=" + points +
                ", ownerContact=" + ownerContact +
                '}';
    }
}
