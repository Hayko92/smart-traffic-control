package smarttraffic.vehicle_service.mapper;

import smarttraffic.vehicle_service.dto.AddressDTO;
import smarttraffic.vehicle_service.dto.OwnerContactDTO;
import smarttraffic.vehicle_service.entity.Address;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class AddresMapper {
    private AddresMapper(){};

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
}
