package smarttraffic.violations_analyzer_service.model;

import java.time.Instant;

public class VehicleSimple {

    private long id;
    private String vinNumber;
    private String plateNumber;
    private int horsePower;
    private String Color;
    private VehicleMark mark;
    private VehicleModel model;
    private int productionYear;
    private Instant insuranceExpiry;
    private Instant techInspectionExpiry;
    private boolean checked;

    public VehicleSimple() {
    }

    public VehicleSimple(String vinNumber, String plateNumber, int horsePower, String color, VehicleMark mark, VehicleModel model,
                         int productionYear, Instant insuranceExpiry, Instant techInspectionExpiry, boolean checked, Owner owner) {
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

}
