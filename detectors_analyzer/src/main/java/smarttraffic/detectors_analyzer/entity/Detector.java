package smarttraffic.detectors_analyzer.entity;

import org.hibernate.Hibernate;
import java.util.Map;
import java.util.Objects;

public class Detector {

    private long id;

    private String place;

    private Map<Detector,Integer> previousDetectorsDistance;

    public Detector() {
    }

    public Detector(int id, String place, Map<Detector, Integer> previousDetectors, Detector nextCamId) {
        this.id = id;
        this.place = place;
        this.previousDetectorsDistance = previousDetectors;
    }

    public long getId() {
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

    public Map<Detector, Integer> getPreviousDetectorsDistance() {
        return previousDetectorsDistance;
    }

    public void setPreviousDetectorsDistance(Map<Detector, Integer> previousDetectors) {
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