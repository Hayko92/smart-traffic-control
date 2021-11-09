package smarttraffic.violations_analyzer_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("violation")
public class Violation implements Cloneable {
    @Id
    private long id;
    private String number;
    private String place;
    private Instant creationDate;
    private int price;
    private String type;
    private String photoUrl1;
    private String photoUrl2;
    private Owner owner;
    private Vehicle vehicle;

    public Violation() {
    }

    public Violation(String number, String place, Instant creationDate, int price, String type, String photoUrl1, String photoUrl2, Owner owner, Vehicle vehicle) {
        this.number = number;
        this.place = place;
        this.creationDate = creationDate;
        this.price = price;
        this.type = type;
        this.photoUrl1 = photoUrl1;
        this.photoUrl2 = photoUrl2;
        this.owner = owner;
        this.vehicle = vehicle;
    }

    public Violation(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
