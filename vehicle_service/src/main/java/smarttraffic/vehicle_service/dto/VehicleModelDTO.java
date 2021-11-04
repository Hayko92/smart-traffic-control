package smarttraffic.vehicle_service.dto;

public class VehicleModelDTO {
    private int id;
    private String modelName;
    private VehicleMarkDTO vehicleMark;

    public VehicleModelDTO() {
    }

    public VehicleModelDTO(String modelName, VehicleMarkDTO vehicleMark) {
        this.modelName = modelName;
        this.vehicleMark = vehicleMark;
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

    public VehicleMarkDTO getVehicleMark() {
        return vehicleMark;
    }

    public void setVehicleMark(VehicleMarkDTO vehicleMark) {
        this.vehicleMark = vehicleMark;
    }
}
