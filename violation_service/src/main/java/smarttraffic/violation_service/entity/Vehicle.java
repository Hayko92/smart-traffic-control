package smarttraffic.violation_service.entity;


import javax.persistence.*;
import java.time.Instant;


@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "vin_number")
    private String vinNumber;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "horse_power")
    private int horsPower;

    @Column(name = "color")
    private String Color;

    @Column(name = "mark")
    private String mark;

    @Column(name = "model")
    private String model;

    @Column(name = "production_year")
    private int productionYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "insurance_expiry_date")
    private Instant insuranceExpiry;

    @Column(name = "tech_inspection_expiry_date")
    private Instant techInspectionExpiry;

    @Column(name = "checked")
    private boolean checked;

    public Vehicle() {
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getHorsPower() {
        return horsPower;
    }

    public void setHorsPower(int horsPower) {
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

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Instant getInsuranceExpiry() {
        return insuranceExpiry;
    }

    public void setInsuranceExpiry(Instant insuranceExpiry) {
        this.insuranceExpiry = insuranceExpiry;
    }

    public Instant getTechInspectionExpiry() {
        return techInspectionExpiry;
    }

    public void setTechInspectionExpiry(Instant techInspectionExpiry) {
        this.techInspectionExpiry = techInspectionExpiry;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
