package smarttraffic.violations_analyzer_service.model;

import java.util.Map;

public class DetectorDto {
    private int id;
    private String place;
    private Map<String, Integer> previousDetectorsDistance;

    public DetectorDto(int id, String place, Map<String, Integer> previousDetectorsDistance) {
        this.id = id;
        this.place = place;
        this.previousDetectorsDistance = previousDetectorsDistance;
    }

    public DetectorDto() {
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

    public void setPreviousDetectorsDistance(Map<String, Integer> previousDetectorsDistance) {
        this.previousDetectorsDistance = previousDetectorsDistance;
    }
}
