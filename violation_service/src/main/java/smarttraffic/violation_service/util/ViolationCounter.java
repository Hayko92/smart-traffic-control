package smarttraffic.violation_service.util;

public final class ViolationCounter {

    private static PointsCounter pointsCounter;
//    private static boolean isDriverVeryBad = false;   if we try to change violation prise calculation logic

    private ViolationCounter() {
    }

    public static int countSpeedViolationBasePrice(int speed) {
        int overSpeed = (speed - 70);
        int result = 0;
        if (overSpeed > 0 && overSpeed <= 10) result = overSpeed * 1000;
        else if (overSpeed > 10 && overSpeed <= 30) result = 20000;
        else if (overSpeed > 30 && overSpeed <= 50) result = 25000;
        else if (overSpeed > 50 && overSpeed <= 80) result = 29000;
        else if (overSpeed > 80) {
            result = 200000;
//            veryBadDriver = true;   if we try to change violation prise calculation logic
        }
        return result;
    }

    public static int countInsurancePrice() {
        int result = 5000;
//        if (days > 1) result += 5000;  if we try to change violation prise calculation logic
//        if (result > 100000) {
//            result = 100000;
//            isDriverVeryBad = true;
//        }
        return result;
    }

    public static int techInspection() {
        int result = 10000;
//        if (days) result += 10000;  if we try to change violation prise calculation logic
//        if (result > 100000) {
//            result = 1000000;
//            isDriverVeryBad = true;
//        }
        return result;
    }

}
