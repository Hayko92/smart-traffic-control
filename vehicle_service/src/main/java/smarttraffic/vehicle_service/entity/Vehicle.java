package smarttraffic.vehicle_service.entity;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "vin")
    private String vin;

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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
