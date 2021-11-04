package smarttraffic.vehicle_service.mapper;

import smarttraffic.vehicle_service.dto.OwnerContactDTO;
import smarttraffic.vehicle_service.entity.OwnerContact;

public final class OwnerContactMapper {
    private OwnerContactMapper() {
    }

    static OwnerContactDTO mapToDTO(OwnerContact ownerContact) {
        OwnerContactDTO ownerContactDTO = new OwnerContactDTO();
        ownerContactDTO.setId(ownerContact.getId());
        ownerContactDTO.setEmailAddress(ownerContact.getEmailAddress());
        ownerContactDTO.setPhoneNumber(ownerContact.getPhoneNumber());
        ownerContactDTO.setAddressDTO(AddresMapper.mapToDTO(ownerContact.getAddress()));
        return ownerContactDTO;
    }

    public static OwnerContact mapToEntity(OwnerContactDTO ownerContact) {
        OwnerContact ownerContactEntity = new OwnerContact();
        ownerContactEntity.setId(ownerContact.getId());
        ownerContactEntity.setEmailAddress(ownerContact.getEmailAddress());
        ownerContactEntity.setPhoneNumber(ownerContact.getPhoneNumber());
        ownerContactEntity.setAddress(AddresMapper.mapToEntity(ownerContact.getAddressDTO()));
        return ownerContactEntity;
    }
}
