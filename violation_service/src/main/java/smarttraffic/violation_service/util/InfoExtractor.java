package smarttraffic.violation_service.util;

import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.model.SpeedViolation;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public final class InfoExtractor {

    private InfoExtractor() {
    }

    public static Map<String, String> extractViolationInformation(Violation violation) {
        Map<String, String> result = new HashMap<>();
        result.put("id", String.valueOf(violation.getId()));
        result.put("type", violation.getType());
        result.put("plateNumber", violation.getVehicle().getPlateNumber());
        result.put("place", violation.getPlace());
        result.put("photoURL1", violation.getPhotoUrl1());
        result.put("time", violation.getCreationDate().truncatedTo(ChronoUnit.MILLIS).toString());
        result.put("price", String.valueOf(violation.getPrice()));
        result.put("firstName", violation.getOwner().getFirstName());
        result.put("lastName", violation.getOwner().getLastName());
        result.put("phone", violation.getOwner().getOwnerContact().getPhoneNumber());
        result.put("email", violation.getOwner().getOwnerContact().getEmailAddress());
        result.put("vehicleMark", violation.getVehicle().getMark().getMarkName());
        result.put("vehicleModel", violation.getVehicle().getModel().getModelName());
        result.put("vehicleYear", String.valueOf(violation.getVehicle().getProductionYear()));
        if (violation instanceof SpeedViolation) {
            result.put("photoURL2", violation.getPhotoUrl2());
        }
        return result;
    }

}
