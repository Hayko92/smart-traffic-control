package smarttraffic.vehicle_service.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_model")
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model_name")
    private String modelName;

    @ManyToOne()
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

}
