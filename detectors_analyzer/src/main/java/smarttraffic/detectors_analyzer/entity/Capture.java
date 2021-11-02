package smarttraffic.detectors_analyzer.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "capture")
public class Capture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "place")
    private String place;

    @Column(name = "time_stamp")
    private Instant instant;

    @Column(name = "photo_url")
    private String photoUrl;


    public Capture() {
    }

    public Capture(String plateNumber, String photoUrl, String place, Instant instant) {
        this.plateNumber = plateNumber;
        this.photoUrl = photoUrl;
        this.place = place;
        this.instant = instant;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String number) {
        this.plateNumber = number;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capture capture = (Capture) o;
        return id == capture.id && Objects.equals(plateNumber, capture.plateNumber) && Objects.equals(place, capture.place) && Objects.equals(instant, capture.instant) && Objects.equals(photoUrl, capture.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, plateNumber, place, instant, photoUrl);
    }

    @Override
    public String toString() {
        return "Capture{" +
                "id=" + id +
                ", number='" + plateNumber + '\'' +
                ", place='" + place + '\'' +
                ", instant=" + instant +
                '}';
    }
}
