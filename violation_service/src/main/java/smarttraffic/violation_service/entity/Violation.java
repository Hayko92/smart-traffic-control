package smarttraffic.violation_service.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "violation_report")
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "place")
    private String place;

    @Column(name = "price")
    private int price;
    @Column(name = "type")
    private String type;

    @Column(name = "photoUrl1")
    private String photoUrl1;

    @Column(name = "photoUrl2")
    private String photoUrl2;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}