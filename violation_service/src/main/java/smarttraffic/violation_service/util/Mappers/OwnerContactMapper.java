package smarttraffic.violation_service.util.Mappers;

import smarttraffic.violation_service.dto.OwnerContactDTO;
import smarttraffic.violation_service.entity.OwnerContact;

public class OwnerContactMapper {
    public OwnerContactMapper() {
    }

    public static OwnerContactDTO mapOwnerContactDTO(OwnerContact ownerContact) {
        OwnerContactDTO ownerContactDTO = new OwnerContactDTO();
        ownerContactDTO.setId(ownerContact.getId());
        ownerContactDTO.setAddress(ownerContact.getAddress());
        ownerContactDTO.setPhoneNumber(ownerContact.getPhoneNumber());
        ownerContactDTO.setEmailAddress(ownerContact.getEmailAddress());
        return ownerContactDTO;
    }

    public static OwnerContact mapOwnerContact(OwnerContactDTO ownerContactDTO) {
        OwnerContact ownerContact = new OwnerContact();
        ownerContact.setId(ownerContactDTO.getId());
        ownerContact.setAddress(ownerContactDTO.getAddress());
        ownerContact.setPhoneNumber(ownerContactDTO.getPhoneNumber());
        ownerContact.setEmailAddress(ownerContactDTO.getEmailAddress());
        return ownerContact;
    }
}
