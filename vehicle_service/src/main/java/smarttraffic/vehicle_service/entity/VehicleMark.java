package smarttraffic.vehicle_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicUpdate
@Table(name = "vehicle_mark")
public class VehicleMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mark_name")
    private String markName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vehicleMark", cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    private Set<VehicleModel> models;

    public Set<VehicleModel> getModels() {
        return models;
    }

    public void setModels(Set<VehicleModel> models) {
        this.models = models;
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
