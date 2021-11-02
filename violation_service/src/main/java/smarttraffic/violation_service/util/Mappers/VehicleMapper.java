package smarttraffic.violation_service.util.Mappers;

import smarttraffic.violation_service.dto.VehicleDTO;
import smarttraffic.violation_service.entity.Vehicle;

public class VehicleMapper {
    public VehicleMapper() {
    }

    public static VehicleDTO mapToVehicleDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setChecked(vehicle.isChecked());
        vehicleDTO.setOwner(vehicle.getOwner());
        vehicleDTO.setVinNumber(vehicle.getVinNumber());
        vehicleDTO.setPlateNumber(vehicle.getPlateNumber());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setMark(vehicle.getMark());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setHorsePower(vehicle.getHorsPower());
        vehicleDTO.setProductionYear(vehicle.getProductionYear());
        vehicleDTO.setTechInspectionExpiry(vehicle.getTechInspectionExpiry());
        return vehicleDTO;
    }

    public static Vehicle mapToVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDTO.getId());
        vehicle.setChecked(vehicleDTO.isChecked());
        vehicle.setOwner(vehicleDTO.getOwner());
        vehicle.setVinNumber(vehicleDTO.getVinNumber());
        vehicle.setPlateNumber(vehicleDTO.getPlateNumber());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setMark(vehicleDTO.getMark());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setHorsePower(vehicleDTO.getHorsePower());
        vehicle.setProductionYear(vehicleDTO.getProductionYear());
        vehicle.setTechInspectionExpiry(vehicleDTO.getTechInspectionExpiry());
        return vehicle;
    }
}
