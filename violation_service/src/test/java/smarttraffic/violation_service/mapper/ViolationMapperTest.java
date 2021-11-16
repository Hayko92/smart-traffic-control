package smarttraffic.violation_service.mapper;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.entity.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ViolationMapperTest {

    Violation violation = new Violation();
    ViolationDTO violationDTO = new ViolationDTO();
    Owner owner = new Owner();
    OwnerContact ownerContact = new OwnerContact();
    Address address = new Address();
    Vehicle vehicle = new Vehicle();
    VehicleMark vehicleMark = new VehicleMark();
    VehicleModel vehicleModel = new VehicleModel();

    @Test
    void checkMapToDTO() {
        String vehiclePlateNumber = "01AM123";
        violation.setNumber(vehiclePlateNumber);
        violation.setType("INS");
        violation.setPlace("BestPlace");
        violation.setOwner(owner);
        violation.getOwner().setOwnerContact(ownerContact);
        violation.getOwner().getOwnerContact().setAddress(address);
        violation.setVehicle(vehicle);
        violation.getVehicle().setOwner(owner);
        violation.getVehicle().setMark(vehicleMark);
        violation.getVehicle().setModel(vehicleModel);
        violationDTO = ViolationMapper.mapToDto(violation);
        assertThat(violationDTO.getNumber()).isEqualTo(violation.getNumber());
    }
}
