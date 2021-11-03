package smarttraffic.violation_service.mapper;

import smarttraffic.violation_service.dto.OwnerDTO;
import smarttraffic.violation_service.entity.Owner;

public final class OwnerMapper {
    private OwnerMapper() {
    }

    public static OwnerDTO mapToDto(Owner owner) {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(owner.getId());
        ownerDTO.setIdNumber(owner.getIdNumber());
        ownerDTO.setFirstName(owner.getFirstName());
        ownerDTO.setLastName(owner.getLastName());
        ownerDTO.setPoints(owner.getPoints());
        ownerDTO.setLicenseNumber(owner.getLicenseNumber());
        ownerDTO.setOwnerContactDTO(OwnerContactMapper.mapToDTO(owner.getOwnerContact()));
        return ownerDTO;
    }

    public static Owner mapToEntity(OwnerDTO owner1) {
        Owner owner = new Owner();
        owner.setId(owner1.getId());
        owner.setIdNumber(owner1.getIdNumber());
        owner.setFirstName(owner1.getFirstName());
        owner.setLastName(owner1.getLastName());
        owner.setPoints(owner1.getPoints());
        owner.setLicenseNumber(owner1.getLicenseNumber());
        owner.setOwnerContact(OwnerContactMapper.mapToEntity(owner1.getOwnerContactDTO()));

        return owner;
    }
}
