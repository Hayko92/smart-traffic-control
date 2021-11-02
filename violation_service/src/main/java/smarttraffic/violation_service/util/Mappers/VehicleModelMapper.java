package smarttraffic.violation_service.util.Mappers;

import smarttraffic.violation_service.dto.VehicleModelDTO;
import smarttraffic.violation_service.entity.VehicleModel;

public class VehicleModelMapper {
    public VehicleModelMapper() {
    }
    public static VehicleModelDTO mapToVehicleModelDTO(VehicleModel vehicleModel) {
        VehicleModelDTO vehicleModelDTO = new VehicleModelDTO();
        vehicleModelDTO.setId(vehicleModel.getId());
        vehicleModelDTO.setVehicleMark(vehicleModel.getVehicleMark());
        vehicleModelDTO.setModelName(vehicleModel.getModelName());
        return vehicleModelDTO;
    }

    public static VehicleModel mapToViolation(VehicleModelDTO vehicleModelDTO) {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setId(vehicleModelDTO.getId());
        vehicleModel.setVehicleMark(vehicleModelDTO.getVehicleMark());
        vehicleModel.setModelName(vehicleModelDTO.getModelName());
        return vehicleModel;
    }
}
