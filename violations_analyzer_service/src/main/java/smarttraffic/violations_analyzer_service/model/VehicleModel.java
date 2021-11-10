package smarttraffic.violations_analyzer_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("vehicleModel")
public class VehicleModel {

    @Id
    private int id;
    private String modelName;
    private VehicleMark vehicleMark;

    public VehicleModel() {
    }

    public VehicleModel(String modelName, VehicleMark vehicleMark) {
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
