package smarttraffic.violation_service.model;

import smarttraffic.violation_service.dto.ViolationDTO;

public class InsuranceViolation extends ViolationDTO {
    public InsuranceViolation() {
        super();
        this.setType("INS");
    }
}
