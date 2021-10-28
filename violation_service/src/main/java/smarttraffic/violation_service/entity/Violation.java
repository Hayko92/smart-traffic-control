package smarttraffic.violation_service.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "violation_report")
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "violation_date_1")
    private Instant violation_date_1;
    @Column(name = "violation_date_2")
    private Instant violation_date_2;

    private String place;

    @Column(name = "price")
    private int price;
    @Column(name = "type")
    private String type;

    @Column(name = "photoUrl1")
    private String photoUrl1;
    @Column(name = "photoUrl2")
    private String photoUrl2;

    private String number;

    public Violation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getViolation_date_1() {
        return violation_date_1;
    }

    public void setViolation_date_1(Instant violation_date_1) {
        this.violation_date_1 = violation_date_1;
    }

    public Instant getViolation_date_2() {
        return violation_date_2;
    }

    public void setViolation_date_2(Instant violation_date_2) {
        this.violation_date_2 = violation_date_2;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhotoUrl1() {
        return photoUrl1;
    }

    public void setPhotoUrl1(String photoUrl1) {
        this.photoUrl1 = photoUrl1;
    }

    public String getPhotoUrl2() {
        return photoUrl2;
    }

    public void setPhotoUrl2(String photoUrl2) {
        this.photoUrl2 = photoUrl2;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}