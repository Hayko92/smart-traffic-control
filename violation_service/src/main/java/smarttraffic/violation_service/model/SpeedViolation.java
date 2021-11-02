package smarttraffic.violation_service.model;

import smarttraffic.violation_service.dto.ViolationDTO;

public class SpeedViolation extends ViolationDTO {

    public SpeedViolation() {
        super();
        this.setType("SPEED");
    }
}
