package smarttraffic.violation_service.model;

import smarttraffic.violation_service.entity.Violation;

public class SpeedViolation extends Violation {

    public SpeedViolation() {
        super();
        this.setType("SPEED");
    }
}
