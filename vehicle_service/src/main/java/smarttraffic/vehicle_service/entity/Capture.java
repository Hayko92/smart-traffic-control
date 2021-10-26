package smarttraffic.vehicle_service.entity;


import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "capture")
public class Capture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "place")
    private String place;

    @Column(name = "time_stamp")
    private Instant instant;

    @ElementCollection
    private List<Integer> violationIds;

    public Capture() {
    }

    public Capture(String number, String place, Instant instant) {
        this.number = number;
        this.place = place;
        this.instant = instant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public List<Integer> getViolationIds() {
        return violationIds;
    }

    public void setViolationIds(List<Integer> violationIds) {
        this.violationIds = violationIds;
    }

    public void addViolationId(Integer id) {
        this.violationIds.add(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capture capture = (Capture) o;
        return id == capture.id && Objects.equals(number, capture.number) && Objects.equals(place, capture.place) && Objects.equals(instant, capture.instant) && Objects.equals(violationIds, capture.violationIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, place, instant, violationIds);
    }

    @Override
    public String toString() {
        return "Capture{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", place='" + place + '\'' +
                ", instant=" + instant +
                ", violationIds=" + violationIds +
                '}';
    }
}
