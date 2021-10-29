package smarttraffic.cameraimitation.dto;

import java.util.Map;

public class DetectorDTO {
    private int id;
    private String place;
    private Map<DetectorDTO, Integer> previousDetectorsDistance;

    public DetectorDTO(int id, String place, Map<DetectorDTO, Integer> previousDetectorsDistance) {
        this.id = id;
        this.place = place;
        this.previousDetectorsDistance = previousDetectorsDistance;
    }

    public DetectorDTO() {
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

    public Map<DetectorDTO, Integer> getPreviousDetectorsDistance() {
        return previousDetectorsDistance;
    }

    public void setPreviousDetectorsDistance(Map<DetectorDTO, Integer> previousDetectorsDistance) {
        this.previousDetectorsDistance = previousDetectorsDistance;
    }
}
