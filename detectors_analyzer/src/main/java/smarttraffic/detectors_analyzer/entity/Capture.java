package smarttraffic.detectors_analyzer.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Capture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "photo_URL")
    private String photoURL;

    @Column(name = "capture_time")
    private Timestamp captureTime;


    public Capture() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Timestamp getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Timestamp captureTime) {
        this.captureTime = captureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capture capture = (Capture) o;
        return id == capture.id && Objects.equals(photoURL, capture.photoURL) && Objects.equals(captureTime, capture.captureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoURL, captureTime);
    }
}
