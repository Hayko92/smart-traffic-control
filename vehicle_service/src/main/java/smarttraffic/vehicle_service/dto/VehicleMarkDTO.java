package smarttraffic.vehicle_service.dto;

public class VehicleMarkDTO {
    private long id;
    private String markName;


    public VehicleMarkDTO() {
    }

    public VehicleMarkDTO(String markName) {
        this.markName = markName;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

}
