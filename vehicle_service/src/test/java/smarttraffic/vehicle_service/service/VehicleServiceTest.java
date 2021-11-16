//package smarttraffic.vehicle_service.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import smarttraffic.vehicle_service.entity.*;
//import smarttraffic.vehicle_service.repository.VehicleRepository;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class VehicleServiceTest {
//
//    @Autowired
//    private VehicleServiceImpl vehicleServiceImpl;
//    @Autowired
//    private VehicleRepository vehicleRepository;
//
//    private final Vehicle vehicle = new Vehicle();
//    private final Owner owner = new Owner();
//    private final OwnerContact ownerContact = new OwnerContact();
//    private final Address address = new Address();
//    private final VehicleModel vehicleModel = new VehicleModel();
//    private final VehicleMark vehicleMark = new VehicleMark();
//    private final Set<Vehicle> vehicles = new HashSet<>();
//
//    @Test
//    void checkGetByNumber() {
//
//        vehicleMark.setMarkName("Model_S");
//        vehicleModel.setModelName("Tesla");
//        vehicleModel.setVehicleMark(vehicleMark);
//
//        vehicle.setPlateNumber("01AM123");
//        vehicle.setVinNumber("XXXXXX11XXXXXX");
//        vehicle.setColor("White");
//
//        owner.setFirstName("Aram");
//        owner.setLastName("Mirzoyan");
//        owner.setLicenseNumber("AU11111");
//        owner.setIdNumber("000112233");
//        owner.setVehicles(vehicles);
//        vehicle.setOwner(owner);
//        vehicle.getOwner().setOwnerContact(ownerContact);
//        vehicle.getOwner().getOwnerContact().setAddress(address);
//        vehicle.setMark(vehicleMark);
//        vehicle.setModel(vehicleModel);
//
//        vehicles.add(vehicle);
//        vehicleRepository.save(vehicle);
//        vehicleServiceImpl.getByNumber("01AM123");
//
//        assertThat(vehicleServiceImpl.getByNumber("01AM123")).isNotNull();
//    }
//}