package smarttraffic.notifiers.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

public class CaptureDto {

    private int id;

    private String plateNumber;

    private String place;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Instant instant;

    private String photoUrl;

    public CaptureDto() {
    }

    public CaptureDto(String plateNumber, String photoUrl, String place, Instant instant) {
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

}
