package smarttraffic.vehicle_service.dto;

public class VehicleModelDTO {
    private long id;
    private String modelName;


    public VehicleModelDTO() {
    }

    public VehicleModelDTO(String modelName) {
        this.modelName = modelName;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

}
