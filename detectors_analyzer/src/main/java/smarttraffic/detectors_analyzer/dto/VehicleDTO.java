package smarttraffic.detectors_analyzer.dto;

import java.time.Instant;

public class VehicleDTO {

    private long id;
    private String plateNumber;
    private Instant insuranceExpiry;
    private Instant techInspectionExpiry;
    private boolean checked;

    public VehicleDTO() {
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

}
