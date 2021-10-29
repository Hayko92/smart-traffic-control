package smarttraffic.vehicle_service.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class VehicleMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mark_name")
    private String markName;

    @OneToMany(mappedBy = "vehicleMark")
    private List<VehicleModel> vehicleModels;

    @OneToOne(mappedBy = "mark")
    private Vehicle vehicle;

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
