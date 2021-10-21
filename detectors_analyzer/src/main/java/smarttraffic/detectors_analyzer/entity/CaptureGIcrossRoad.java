package smarttraffic.detectors_analyzer.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "capture_GI_crossroad")
public class CaptureGIcrossRoad  extends Capture{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "photo_URL")
    private String photoURL;

    @Column(name = "capture_time")
    private Timestamp captureTime;
    @Column(name = "number")
    private String number;

    public CaptureGIcrossRoad() {
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
        CaptureGIcrossRoad captureGIcrossRoad = (CaptureGIcrossRoad) o;
        return id == captureGIcrossRoad.id && Objects.equals(photoURL, captureGIcrossRoad.photoURL) && Objects.equals(captureTime, captureGIcrossRoad.captureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoURL, captureTime);
    }
}
