package smarttraffic.violation_service.entity;

import javax.persistence.*;

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

    @Column(name = "dateOfBirthday")
    private int dateOfBirthday;

    @Column(name = "points")
    private int points;

    public Owner() {
    }

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
}
