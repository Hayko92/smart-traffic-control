package smarttraffic.violation_service.util.Mappers;

import smarttraffic.violation_service.dto.TechInspectionViolationDTO;
import smarttraffic.violation_service.entity.TechInspectionViolation;

public class TechInspectionViolationMapper {
    public TechInspectionViolationMapper() {
    }

    public static TechInspectionViolationDTO mapOwnerContactDTO(TechInspectionViolation techInspectionViolation) {
        TechInspectionViolationDTO techInspectionViolationDTO = new TechInspectionViolationDTO();
        techInspectionViolationDTO.setId(techInspectionViolation.getId());
        techInspectionViolationDTO.setType(techInspectionViolation.getType());
        techInspectionViolationDTO.setOwner(techInspectionViolation.getOwner());
        techInspectionViolationDTO.setCreationDate(techInspectionViolation.getCreationDate());
        techInspectionViolationDTO.setNumber(techInspectionViolation.getNumber());
        techInspectionViolationDTO.setPhotoUrl1(techInspectionViolation.getPhotoUrl1());
        techInspectionViolationDTO.setPlace(techInspectionViolation.getPlace());
        techInspectionViolationDTO.setVehicle(techInspectionViolation.getVehicle());
        techInspectionViolationDTO.setPrice(techInspectionViolation.getPrice());
        return techInspectionViolationDTO;
    }

    public static TechInspectionViolation mapTechInspectionViolation(TechInspectionViolationDTO techInspectionViolationDTO) {
        TechInspectionViolation techInspectionViolation = new TechInspectionViolation();
        techInspectionViolation.setId(techInspectionViolationDTO.getId());
        techInspectionViolation.setId(techInspectionViolationDTO.getId());
        techInspectionViolation.setType(techInspectionViolationDTO.getType());
        techInspectionViolation.setOwner(techInspectionViolationDTO.getOwner());
        techInspectionViolation.setCreationDate(techInspectionViolationDTO.getCreationDate());
        techInspectionViolation.setNumber(techInspectionViolationDTO.getNumber());
        techInspectionViolation.setPhotoUrl1(techInspectionViolationDTO.getPhotoUrl1());
        techInspectionViolation.setPlace(techInspectionViolationDTO.getPlace());
        techInspectionViolation.setVehicle(techInspectionViolationDTO.getVehicle());
        techInspectionViolation.setPrice(techInspectionViolationDTO.getPrice());
        return techInspectionViolation;
    }
}
