package smarttraffic.violation_service.entity;


import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "violation")
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "plate_number")
    private String number;

    @Column(name = "place")
    private String place;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "price")
    private int price;

    @Column(name = "type")
    private String type;

    @Column(name = "photo_url1")
    private String photoUrl1;

    @Column(name = "photo_url2")
    private String photoUrl2;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Violation() {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violation violation = (Violation) o;
        return id == violation.id && price == violation.price && number.equals(violation.number) && place.equals(violation.place) && creationDate.equals(violation.creationDate) && type.equals(violation.type) && photoUrl1.equals(violation.photoUrl1) && Objects.equals(photoUrl2, violation.photoUrl2) && owner.equals(violation.owner) && vehicle.equals(violation.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, place, creationDate, price, type, photoUrl1, photoUrl2, owner, vehicle);
    }

    @Override
    public String toString() {
        return "Violation{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", place='" + place + '\'' +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", photoUrl1='" + photoUrl1 + '\'' +
                ", photoUrl2='" + photoUrl2 + '\'' +
                ", owner=" + owner +
                ", vehicle=" + vehicle +
                '}';
    }
}