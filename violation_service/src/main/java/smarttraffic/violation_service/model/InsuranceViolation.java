package smarttraffic.violation_service.model;

import smarttraffic.violation_service.entity.Violation;

public class InsuranceViolation extends Violation {
    public InsuranceViolation() {
        super();
        this.setType("INS");
    }
}
