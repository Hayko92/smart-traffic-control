package smarttraffic.violation_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class CaptureDTO {

    private int id;

    private String plateNumber;

    private String place;

    @JsonFormat(timezone = "Asia/Yerevan")
    private Instant instant;

    private String photoUrl;

    public CaptureDTO() {
    }

    public CaptureDTO(String plateNumber, String photoUrl, String place, Instant instant) {
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
