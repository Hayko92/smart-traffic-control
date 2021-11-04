package smarttraffic.violation_service.mapper;

import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.entity.Violation;

public final class ViolationMapper {
    private ViolationMapper() {
    }

    public static ViolationDTO mapToDto(Violation violation) {
        ViolationDTO violationDTO = new ViolationDTO();
        violationDTO.setId(violation.getId());
        violationDTO.setNumber(violation.getNumber());
        violationDTO.setPlace(violation.getPlace());
        violationDTO.setCreationDate(violation.getCreationDate());
        violationDTO.setPrice(violation.getPrice());
        violationDTO.setType(violation.getType());
        violationDTO.setPhotoUrl1(violation.getPhotoUrl1());
        violationDTO.setOwner(OwnerMapper.mapToDto(violation.getOwner()));
        violationDTO.setVehicle(VehicleMapper.mapToDto(violation.getVehicle()));
        return violationDTO;
    }

    public static Violation mapToEntity(ViolationDTO violationDTO) {
        Violation violation = new Violation();
        violation.setId(violationDTO.getId());
        violation.setNumber(violationDTO.getNumber());
        violation.setPlace(violationDTO.getPlace());
        violation.setCreationDate(violationDTO.getCreationDate());
        violation.setPrice(violationDTO.getPrice());
        violation.setType(violationDTO.getType());
        violation.setPhotoUrl1(violationDTO.getPhotoUrl1());
        if (violationDTO.getType().equals("SPEED")) violation.setPhotoUrl2(violationDTO.getPhotoUrl2());
        violation.setOwner(OwnerMapper.mapToEntity(violationDTO.getOwner()));
        violation.setVehicle(VehicleMapper.mapToEntity(violationDTO.getVehicle()));
        return violation;
    }
}
