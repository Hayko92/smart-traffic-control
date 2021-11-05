package smarttraffic.vehicle_service.mapper;

import smarttraffic.vehicle_service.dto.VehicleMarkDTO;
import smarttraffic.vehicle_service.entity.VehicleMark;

public final class MarkMapper {
    private MarkMapper() {
    }

    public static VehicleMarkDTO mapToDto(VehicleMark vehicleMark) {
        VehicleMarkDTO vehicleMarkDTO = new VehicleMarkDTO();
        vehicleMarkDTO.setId(vehicleMark.getId());
        vehicleMarkDTO.setMarkName(vehicleMark.getMarkName());
        return vehicleMarkDTO;
    }

    public static VehicleMark mapToEntity(VehicleMarkDTO mark) {
        VehicleMark vehicleMark = new VehicleMark();
        vehicleMark.setId(mark.getId());
        vehicleMark.setMarkName(mark.getMarkName());
        return vehicleMark;
    }
}
