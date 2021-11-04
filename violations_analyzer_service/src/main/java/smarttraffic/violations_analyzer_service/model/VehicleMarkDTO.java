package smarttraffic.violations_analyzer_service.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("vehicleMark")
public class VehicleMarkDTO {
    private int id;
    private String markName;
    private Set<VehicleModelDTO> models;

    public VehicleMarkDTO() {
    }

    public VehicleMarkDTO(String markName, Set<VehicleModelDTO> models) {
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

    public Set<VehicleModelDTO> getModels() {
        return models;
    }

    public void setModels(Set<VehicleModelDTO> models) {
        this.models = models;
    }
}
