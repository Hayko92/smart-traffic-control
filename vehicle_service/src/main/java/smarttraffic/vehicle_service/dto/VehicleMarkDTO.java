package smarttraffic.vehicle_service.dto;

import smarttraffic.violation_service.entity.VehicleModel;

import java.util.Set;

public class VehicleMarkDTO {
    private int id;
    private String markName;
    private Set<VehicleModel> models;

    public VehicleMarkDTO() {
    }

    public VehicleMarkDTO(String markName, Set<VehicleModel> models) {
        this.markName = markName;
        this.models = models;
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

    public Set<VehicleModel> getModels() {
        return models;
    }

    public void setModels(Set<VehicleModel> models) {
        this.models = models;
    }
}
