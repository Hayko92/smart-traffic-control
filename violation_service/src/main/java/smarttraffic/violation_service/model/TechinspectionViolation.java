package smarttraffic.violation_service.model;

import smarttraffic.violation_service.dto.ViolationDTO;

public class TechinspectionViolation extends ViolationDTO {
    public TechinspectionViolation() {
        super();
        this.setType("TECH");
    }
}
