package smarttraffic.violation_service.mapper;


import smarttraffic.violation_service.dto.VehicleMarkDTO;
import smarttraffic.violation_service.entity.VehicleMark;

public final class MarkMapper {
    private MarkMapper() {
    }

    public static VehicleMarkDTO mapToDto(VehicleMark vehicleMark) {
        VehicleMarkDTO vehicleMarkDTO = new VehicleMarkDTO();
        vehicleMarkDTO.setId(vehicleMarkDTO.getId());
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
