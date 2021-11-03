package smarttraffic.violation_service.util;

import smarttraffic.violation_service.dto.ViolationDTO;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public final class InfoExtractor {

    private InfoExtractor() {
    }

    public static Map<String, String> extractViolationInformation(ViolationDTO violationDTO) {
        Map<String, String> result = new HashMap<>();
        result.put("id", String.valueOf(violationDTO.getId()));
        result.put("type", violationDTO.getType());
        result.put("plateNumber", violationDTO.getVehicle().getPlateNumber());
        result.put("place", violationDTO.getPlace());
        result.put("photoURL1", violationDTO.getPhotoUrl1());
        result.put("time", violationDTO.getCreationDate().truncatedTo(ChronoUnit.SECONDS).toString());
        result.put("price", String.valueOf(violationDTO.getPrice()));
        result.put("firstName", violationDTO.getOwner().getFirstName());
        result.put("lastName", violationDTO.getOwner().getLastName());
        result.put("phone", violationDTO.getOwner().getOwnerContactDTO().getPhoneNumber());
        result.put("email", violationDTO.getOwner().getOwnerContactDTO().getEmailAddress());
        result.put("vehicleMark", violationDTO.getVehicle().getMark().getMarkName());
        result.put("vehicleModel", violationDTO.getVehicle().getModel().getModelName());
        result.put("vehicleYear", String.valueOf(violationDTO.getVehicle().getProductionYear()));
        if (violationDTO.getType().equals("SPEED")) {
            result.put("photoURL2", violationDTO.getPhotoUrl2());
        }
        return result;
    }

}
