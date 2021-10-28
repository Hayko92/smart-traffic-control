package smarttraffic.violation_service.util;

import smarttraffic.violation_service.entity.SpeedViolation;
import smarttraffic.violation_service.entity.Violation;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public final class InfoExtractor {
    public static Map<String, String> extractViolationInformation(Violation violation) {
        Map<String, String> result = new HashMap<>();
        result.put("id", String.valueOf(violation.getId()));
        result.put("type", violation.getType());
        result.put("plateNumber", violation.getNumber());
        result.put("place", violation.getPlace());
        result.put("photoURL1", violation.getPhotoUrl1());
        result.put("time", violation.getCreationDate().truncatedTo(ChronoUnit.SECONDS).toString());
        result.put("price", String.valueOf(violation.getPrice()));
        result.put("firstName", violation.getOwner().getFirstname());
        result.put("lastName", violation.getOwner().getLastname());
        result.put("vehicleMark", violation.getVehicle().getMark());
        result.put("vehicleModel", violation.getVehicle().getModel());
        result.put("vehicleYear", String.valueOf(violation.getVehicle().getProductionYear()));
        if (violation instanceof SpeedViolation) {
            result.put("photoURL2", violation.getPhotoUrl2());
        }
        return result;
    }

}
