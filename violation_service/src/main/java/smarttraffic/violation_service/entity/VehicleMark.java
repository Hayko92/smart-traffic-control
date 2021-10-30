package smarttraffic.violation_service.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vehicle_mark")
public class VehicleMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mark_name")
    private String markName;

    @OneToMany(mappedBy = "vehicleMark")
    private Set<VehicleModel> models;

    public Set<VehicleModel> getModels() {
        return models;
    }

    public void setModels(Set<VehicleModel> models) {
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
}
