package smarttraffic.violation_service.util;

public final class ViolationCounter {
    private ViolationCounter() {
    }

    public static int countSpeedViolationBasePrice(int speed) {
        int overSpeed = (speed - ViolationConstants.MAX_PERMISSIBLE_SPEED);
        int result = 0;
        if (overSpeed > 0 && overSpeed <= ViolationConstants.FIRST_SPEED_RANGE) result = overSpeed * 1000;
        else if (overSpeed > ViolationConstants.FIRST_SPEED_RANGE && overSpeed <= ViolationConstants.SECOND_SPEED_RANGE)
            result = ViolationConstants.VIOLATION_COST_FIRST;
        else if (overSpeed > ViolationConstants.SECOND_SPEED_RANGE && overSpeed <= ViolationConstants.THIRD_SPEED_RANGE)
            result = ViolationConstants.VIOLATION_COST_SECOND;
        else if (overSpeed > ViolationConstants.THIRD_SPEED_RANGE && overSpeed <= ViolationConstants.THOURTH_SPEED_RANGE)
            result = ViolationConstants.VIOLATION_COST_THIRD;
        else if (overSpeed > ViolationConstants.THOURTH_SPEED_RANGE) {
            result = ViolationConstants.VIOLATION_COST_THOURTH;
        }
        return result;
    }

}
