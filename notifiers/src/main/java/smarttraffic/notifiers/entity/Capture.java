package smarttraffic.notifiers.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.Objects;

public class Capture {

    private int id;

    private String plateNumber;

    private String place;
    @JsonFormat(timezone = "Asia/Yerevan")
    private Instant instant;

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
        return id == capture.id && Objects.equals(plateNumber, capture.plateNumber) && Objects.equals(place, capture.place) && Objects.equals(instant, capture.instant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, plateNumber, place, instant);
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