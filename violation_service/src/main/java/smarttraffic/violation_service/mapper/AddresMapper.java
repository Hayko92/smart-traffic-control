package smarttraffic.violation_service.mapper;

import smarttraffic.violation_service.dto.AddressDTO;
import smarttraffic.violation_service.entity.Address;

public final class AddresMapper {
    private AddresMapper() {
    }

    public static AddressDTO mapToDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setBuilding(address.getBuilding());
        addressDTO.setZipCode(address.getZipCode());
        return addressDTO;
    }

    public static Address mapToEntity(AddressDTO address) {
        Address addressEntity = new Address();
        addressEntity.setId(address.getId());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setCity(address.getCity());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setBuilding(address.getBuilding());
        addressEntity.setZipCode(address.getZipCode());
        return addressEntity;
    }
}
