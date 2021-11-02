package smarttraffic.violation_service.util.Mappers;

import smarttraffic.violation_service.dto.AddressDTO;
import smarttraffic.violation_service.entity.Address;

public class AddressMapper {
    private AddressMapper() {
    }

    public static AddressDTO mapToAddressDTO(Address address) {
        AddressDTO detectorDTO = new AddressDTO();
        detectorDTO.setId(address.getId());
        detectorDTO.setZipCode(address.getZipCode());
        detectorDTO.setCountry(address.getCountry());
        detectorDTO.setCity(address.getCity());
        detectorDTO.setStreet(address.getStreet());
        detectorDTO.setBuilding(address.getBuilding());
        detectorDTO.setOwners(address.getOwners());
        detectorDTO.setStreet(address.getStreet());
        return detectorDTO;
    }

    public static Address mapToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setZipCode(addressDTO.getZipCode());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getBuilding());
        address.setBuilding(addressDTO.getBuilding());
        address.setApartment(addressDTO.getApartment());
        address.setOwners(addressDTO.getOwners());
        return address;
    }
}
