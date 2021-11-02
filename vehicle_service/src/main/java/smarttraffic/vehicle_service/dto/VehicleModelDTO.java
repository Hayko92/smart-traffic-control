package smarttraffic.vehicle_service.dto;

import smarttraffic.violation_service.entity.VehicleMark;

public class VehicleModelDTO {
    private int id;
    private String modelName;
    private VehicleMark vehicleMark;

    public VehicleModelDTO() {
    }

    public VehicleModelDTO(String modelName, VehicleMark vehicleMark) {
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

    public VehicleMark getVehicleMark() {
        return vehicleMark;
    }

    public void setVehicleMark(VehicleMark vehicleMark) {
        this.vehicleMark = vehicleMark;
    }
}
