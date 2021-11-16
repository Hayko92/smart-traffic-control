package smarttraffic.vehicle_service.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VehicleDTOTest {
    VehicleDTO vehicleDTO = new VehicleDTO();

    @Test
    void violationDTOTest() {
        String vehiclePlateNumber = "01AM123";
        vehicleDTO.setPlateNumber(vehiclePlateNumber);
        assertThat(vehicleDTO.getPlateNumber()).isEqualTo(vehiclePlateNumber);

        vehicleDTO.setId(123456L);
        assertThat(vehicleDTO.getId()).isEqualTo(123456L);
    }
}