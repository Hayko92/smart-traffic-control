package smarttraffic.vehicle_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.vehicle_service.controller.VehicleServiceController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VehicleServiceApplicationTests {

    @Autowired
    VehicleServiceController vehicleServiceController;

    @Test
    void contextLoads() {
        assertThat(vehicleServiceController).isNotNull();
    }
}