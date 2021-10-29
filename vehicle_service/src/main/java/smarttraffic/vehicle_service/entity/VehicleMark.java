package smarttraffic.vehicle_service.entity;

import javax.persistence.*;

@Entity
public class VehicleMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mark_name")
    private String markName;

}
