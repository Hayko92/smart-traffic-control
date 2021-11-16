package smarttraffic.vehicle_service.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.vehicle_service.dto.VehicleDTO;
import smarttraffic.vehicle_service.entity.*;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VehicleMapperTest {

    Vehicle vehicle = new Vehicle();
    VehicleDTO vehicleDTO = new VehicleDTO();
    Owner owner = new Owner();
    OwnerContact ownerContact = new OwnerContact();
    Address address = new Address();
    VehicleMark vehicleMark = new VehicleMark();
    VehicleModel vehicleModel = new VehicleModel();

    @Test
    void checkMapToDTO() {
        String vehiclePlateNumber = "01AM123";
        vehicle.setPlateNumber(vehiclePlateNumber);
        vehicle.setVinNumber("XXXXXX11XXXXXX");
        vehicle.setColor("White");
        Set<Vehicle> vehicles = new HashSet<>();
        vehicles.add(vehicle);
        owner.setFirstName("Aram");
        owner.setVehicles(vehicles);
        vehicle.setOwner(owner);
        vehicle.getOwner().setOwnerContact(ownerContact);
        vehicle.getOwner().getOwnerContact().setAddress(address);
        vehicle.setMark(vehicleMark);
        vehicle.setModel(vehicleModel);
        vehicleDTO = VehicleMapper.mapToDto(vehicle);
        assertThat(vehicleDTO.getPlateNumber()).isEqualTo(vehicle.getPlateNumber());
    }
}
