package com.example.cameraimitation.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "detector")
public class Detector implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "place")
    private String place;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "nextCamId")
    private List<Detector> previousDetectors;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "next_cam_id")
    private Detector nextCamId;

    @Column(name = "distance_from_next_detector")
    private Integer distanceFromNextDetector;

    public List<Detector> getPreviousDetectors() {
        return previousDetectors;
    }

    public Detector() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPreviousDetectors(List<Detector> previousDetectors) {
        this.previousDetectors = previousDetectors;
    }

    public Detector getNextCamId() {
        return nextCamId;
    }

    public void setNextCamId(Detector nextCamId) {
        this.nextCamId = nextCamId;
    }

    public Integer getDistanceFromNextDetector() {
        return distanceFromNextDetector;
    }

    public void setDistanceFromNextDetector(Integer distanceFromNextDetector) {
        this.distanceFromNextDetector = distanceFromNextDetector;
    }

    public Detector(int id, String name) {
        this.id = id;
        this.name = name;
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
