package smarttraffic.violation_service.dto;

public class VehicleModelDTO {
    private int id;
    private String modelName;


    public VehicleModelDTO() {
    }

    public VehicleModelDTO(String modelName) {
        this.modelName = modelName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

}
