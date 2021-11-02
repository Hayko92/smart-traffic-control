package smarttraffic.violation_service.util.Mappers;

import smarttraffic.violation_service.dto.VehicleMarkDTO;
import smarttraffic.violation_service.entity.VehicleMark;

public class VehicleMarkMapper {
    public VehicleMarkMapper() {
    }

    public static VehicleMarkDTO mapToVehicleMarkDTO(VehicleMark vehicleMark) {
        VehicleMarkDTO vehicleMarkDTO = new VehicleMarkDTO();
        vehicleMarkDTO.setId(vehicleMark.getId());
        vehicleMarkDTO.setModels(vehicleMark.getModels());
        vehicleMarkDTO.setMarkName(vehicleMark.getMarkName());
        return vehicleMarkDTO;
    }

    public static VehicleMark mapToVehicleMark(VehicleMarkDTO vehicleMarkDTO) {
        VehicleMark vehicleMark = new VehicleMark();
        vehicleMark.setId(vehicleMarkDTO.getId());
        vehicleMark.setModels(vehicleMarkDTO.getModels());
        vehicleMark.setMarkName(vehicleMarkDTO.getMarkName());
        return vehicleMark;
    }
}
