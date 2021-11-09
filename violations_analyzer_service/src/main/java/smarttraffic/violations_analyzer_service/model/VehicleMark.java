package smarttraffic.violations_analyzer_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("vehicleMark")
public class VehicleMark {
    @Id
    private int id;
    private String markName;
    private Set<VehicleModel> models;

    public VehicleMark() {
    }

    public VehicleMark(String markName, Set<VehicleModel> models) {
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
