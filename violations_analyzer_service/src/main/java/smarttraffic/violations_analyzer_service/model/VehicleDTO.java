package smarttraffic.violations_analyzer_service.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("vehicle")
public class VehicleDTO {
    private long id;
    private String vinNumber;
    private String plateNumber;
    private int horsePower;
    private String Color;
    private VehicleMarkDTO mark;
    private VehicleModelDTO model;
    private int productionYear;
    private Instant insuranceExpiry;
    private Instant techInspectionExpiry;
    private boolean checked;
    private OwnerDTO owner;

    public VehicleDTO() {
    }

    public VehicleDTO(String vinNumber, String plateNumber, int horsePower, String color, VehicleMarkDTO mark, VehicleModelDTO model,
                      int productionYear, Instant insuranceExpiry, Instant techInspectionExpiry, boolean checked, OwnerDTO owner) {
        this.vinNumber = vinNumber;
        this.plateNumber = plateNumber;
        this.horsePower = horsePower;
        Color = color;
        this.mark = mark;
        this.model = model;
        this.productionYear = productionYear;
        this.insuranceExpiry = insuranceExpiry;
        this.techInspectionExpiry = techInspectionExpiry;
        this.checked = checked;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public VehicleMarkDTO getMark() {
        return mark;
    }

    public void setMark(VehicleMarkDTO mark) {
        this.mark = mark;
    }

    public VehicleModelDTO getModel() {
        return model;
    }

    public void setModel(VehicleModelDTO model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
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

    public OwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }
}
