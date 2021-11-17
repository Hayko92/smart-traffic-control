package smarttraffic.vehicle_service.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import smarttraffic.vehicle_service.entity.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VehicleRepositoryTest {

    private final Vehicle vehicle = new Vehicle();
    @Autowired
    private VehicleRepository repository;

    @Test
    void saveVehicle() {
        String vehiclePlateNumber = "01AM123";
        vehicle.setPlateNumber(vehiclePlateNumber);
        vehicle.setVinNumber("XXXX111XXXXX");
        vehicle.setColor("White");
        long countBeforeSave = repository.count();
        repository.save(vehicle);
        assertThat(repository.count()).isEqualTo(countBeforeSave + 1);
    }
}
