package smarttraffic.violation_service.util.Mappers;

import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.entity.Violation;

public class ViolationMapper {
    public ViolationMapper() {
    }

    public static ViolationDTO mapToAddressDTO(Violation violation) {
        ViolationDTO violationDTO = new ViolationDTO();
        violationDTO.setId(violation.getId());
        violationDTO.setNumber(violation.getNumber());
        violationDTO.setPlace(violation.getPlace());
        violationDTO.setCreationDate(violation.getCreationDate());
        violationDTO.setPrice(violation.getPrice());
        violationDTO.setType(violation.getType());
        violationDTO.setPhotoUrl1(violation.getPhotoUrl1());
        violationDTO.setOwner(violation.getOwner());
        violationDTO.setVehicle(violation.getVehicle());
        return violationDTO;
    }

    public static Violation mapToViolation(ViolationDTO violationDTO) {
        Violation violation = new Violation();
        violation.setId(violationDTO.getId());
        violation.setNumber(violationDTO.getNumber());
        violation.setPlace(violationDTO.getPlace());
        violation.setCreationDate(violationDTO.getCreationDate());
        violation.setPrice(violationDTO.getPrice());
        violation.setType(violationDTO.getType());
        violation.setPhotoUrl1(violationDTO.getPhotoUrl1());
        violation.setOwner(violationDTO.getOwner());
        violation.setVehicle(violationDTO.getVehicle());
        return violation;
    }
}
