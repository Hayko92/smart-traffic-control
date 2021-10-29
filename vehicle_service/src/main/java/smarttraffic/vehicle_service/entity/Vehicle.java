package smarttraffic.vehicle_service.entity;


import javax.persistence.*;


@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "horse_power")
    private String horsPower;

    @Column(name = "color")
    private String Color;

    @Column(name = "mark")
    private String mark;

    @Column(name = "production_year")
    private int productionYear;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Vehicle() {
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getHorsPower() {
        return horsPower;
    }

    public void setHorsPower(String horsPower) {
        this.horsPower = horsPower;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

}
