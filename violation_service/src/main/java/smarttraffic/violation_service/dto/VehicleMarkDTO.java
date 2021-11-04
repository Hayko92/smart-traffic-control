package smarttraffic.violation_service.dto;

public class VehicleMarkDTO {
    private int id;
    private String markName;


    public VehicleMarkDTO() {
    }

    public VehicleMarkDTO(String markName) {
        this.markName = markName;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

}
