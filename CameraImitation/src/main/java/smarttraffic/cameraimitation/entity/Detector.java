package smarttraffic.cameraimitation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "detector")
@Embeddable

public class Detector {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "place")
    private String place;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, Integer> previousDetectorsDistance;

    public Detector() {
    }

    public Detector(int id, String place, Map<String, Integer> previousDetectors, Detector nextCamId) {
        this.id = id;
        this.place = place;
        this.previousDetectorsDistance = previousDetectors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Map<String, Integer> getPreviousDetectorsDistance() {
        return previousDetectorsDistance;
    }

    public void setPreviousDetectorsDistance(Map<String, Integer> previousDetectors) {
        this.previousDetectorsDistance = previousDetectors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Detector detector = (Detector) o;
        return Objects.equals(id, detector.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
