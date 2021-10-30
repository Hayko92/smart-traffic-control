package smarttraffic.violation_service.model;

import smarttraffic.violation_service.entity.Violation;

public class TechinspectionViolation extends Violation {
    public TechinspectionViolation() {
        super();
        this.setType("TECH");
    }
}
