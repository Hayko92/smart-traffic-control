package smarttraffic.violation_service.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicle_model")
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model_name")
    private String modelName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "mark_id")
    private VehicleMark vehicleMark;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleModel that = (VehicleModel) o;
        return id == that.id && Objects.equals(modelName, that.modelName) && Objects.equals(vehicleMark, that.vehicleMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelName, vehicleMark);
    }

    @Override
    public String toString() {
        return "VehicleModel{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", vehicleMark=" + vehicleMark +
                '}';
    }
}
