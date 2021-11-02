package smarttraffic.violation_service.util.Mappers;

import smarttraffic.violation_service.dto.OwnerDTO;
import smarttraffic.violation_service.entity.Owner;

public class OwnerMapper {

    private OwnerMapper() {

    }

    public static OwnerDTO mapToOwnerDTO(Owner owner) {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(owner.getId());
        ownerDTO.setIdNumber(owner.getIdNumber());
        ownerDTO.setFirstName(owner.getFirstName());
        ownerDTO.setLastName(owner.getLastName());
        ownerDTO.setLicenseNumber(owner.getLicenseNumber());
        ownerDTO.setPoints(owner.getPoints());
        return ownerDTO;
    }

    public static Owner mapToOwner(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setId(ownerDTO.getId());
        owner.setIdNumber(ownerDTO.getIdNumber());
        owner.setFirstName(ownerDTO.getFirstName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setLicenseNumber(ownerDTO.getLicenseNumber());
        owner.setPoints(ownerDTO.getPoints());
        return owner;
    }
}
