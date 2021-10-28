package smarttraffic.violation_service.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vehicle_owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "date_of_birthday")
    private int dateOfBirthday;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email_address")
    private String email;

    @Column(name = "points")
    private int points;
// todo add addres field and refference to database

    @OneToMany(mappedBy = "owner")
    private Set<Vehicle> vehicleSet;
    public Owner() {
    }
    @ManyToOne()
    @JoinColumn(name = "address_id")
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(int birthYear) {
        this.dateOfBirthday = birthYear;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public int getReducedPoint() {
        points--;
        return points;
    }

    public Set<Vehicle> getVehicleSet() {
        return vehicleSet;
    }

    public void setVehicleSet(Set<Vehicle> vehicleSet) {
        this.vehicleSet = vehicleSet;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public  Vehicle getVehicleByPlateNUmber(String plateNumber){
        return vehicleSet.stream()
                .filter(e->e.getNumber().equals(plateNumber))
                .findAny()
                .orElse(null);
    }
}
