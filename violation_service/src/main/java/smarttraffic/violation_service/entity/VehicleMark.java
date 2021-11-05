package smarttraffic.violation_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vehicle_mark")
public class VehicleMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mark_name")
    private String markName;

    @OneToMany(mappedBy = "vehicleMark", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleMark that = (VehicleMark) o;
        return id == that.id && Objects.equals(markName, that.markName) && Objects.equals(models, that.models);
    }

    @Override
    public String toString() {
        return "VehicleMark{" +
                "id=" + id +
                ", markName='" + markName + '\'' +
                ", models=" + models +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, markName, models);
    }

}
