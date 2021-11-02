package smarttraffic.vehicle_service.dto;

import smarttraffic.violation_service.entity.Violation;

public class TechInspectionViolationDTO extends Violation {
    public TechInspectionViolationDTO() {
        super();
        this.setType("TECH");
    }

}
