package smarttraffic.vehicle_service.mapper;

import smarttraffic.vehicle_service.dto.OwnerDTO;
import smarttraffic.vehicle_service.entity.Owner;

import java.util.stream.Collectors;

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
        ownerDTO.setVehicleDTOSet(owner.getVehicles()
                .stream()
                .map(VehicleMapper::mapToSimpleDto)
                .collect(Collectors.toSet()));
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
        owner.setVehicles(owner1.getVehicleDTOSet()
                .stream()
                .map(VehicleMapper::mapToEntity)
                .collect(Collectors.toSet()));
        return owner;
    }

    public static Owner mapToEntityWithoutVehicles(OwnerDTO owner1) {
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
