package smarttraffic.violation_service.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;


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
    private int horsePower;

    @Column(name = "color")
    private String Color;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "mark_id")
    private VehicleMark mark;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "model_id")
    private VehicleModel model;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "insurance_expiry_date")
    private Instant insuranceExpiry;

    @Column(name = "tech_inspection_expiry_date")
    private Instant techInspectionExpiry;

    @Column(name = "checked")
    private boolean checked;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Owner owner;

    public Vehicle() {
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getHorsPower() {
        return horsePower;
    }

    public void setHorsPower(int horsPower) {
        this.horsePower = horsPower;
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

    public VehicleMark getMark() {
        return mark;
    }

    public void setMark(VehicleMark mark) {
        this.mark = mark;
    }

    public VehicleModel getModel() {
        return model;
    }

    public void setModel(VehicleModel model) {
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

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id && horsePower == vehicle.horsePower && productionYear == vehicle.productionYear && checked == vehicle.checked && vinNumber.equals(vehicle.vinNumber) && Objects.equals(plateNumber, vehicle.plateNumber) && Objects.equals(Color, vehicle.Color) && Objects.equals(mark, vehicle.mark) && Objects.equals(model, vehicle.model) && Objects.equals(insuranceExpiry, vehicle.insuranceExpiry) && Objects.equals(techInspectionExpiry, vehicle.techInspectionExpiry) && Objects.equals(owner, vehicle.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vinNumber, plateNumber, horsePower, Color, mark, model, productionYear, insuranceExpiry, techInspectionExpiry, checked, owner);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vinNumber='" + vinNumber + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", horsePower=" + horsePower +
                ", Color='" + Color + '\'' +
                ", mark=" + mark +
                ", model=" + model +
                ", productionYear=" + productionYear +
                ", insuranceExpiry=" + insuranceExpiry +
                ", techInspectionExpiry=" + techInspectionExpiry +
                ", checked=" + checked +
                ", owner=" + owner +
                '}';
    }
}
