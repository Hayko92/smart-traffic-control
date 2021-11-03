package smarttraffic.violation_service.mapper;

import smarttraffic.violation_service.dto.VehicleModelDTO;
import smarttraffic.violation_service.entity.VehicleModel;

public final class ModelMapper {
    private ModelMapper() {
    }

    public static VehicleModelDTO mapToDto(VehicleModel vehicleModel) {
        VehicleModelDTO vehicleModelDTO = new VehicleModelDTO();
        vehicleModelDTO.setId(vehicleModelDTO.getId());
        vehicleModelDTO.setModelName(vehicleModel.getModelName());
        return vehicleModelDTO;
    }

    public static VehicleModel mapToEntity(VehicleModelDTO model) {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setId(model.getId());
        vehicleModel.setModelName(model.getModelName());
        return vehicleModel;
    }
}
