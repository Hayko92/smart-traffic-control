package smarttraffic.vehicle_service.mapper;

import smarttraffic.vehicle_service.dto.VehicleDTO;
import smarttraffic.vehicle_service.entity.Vehicle;

public final class VehicleMapper {
    private VehicleMapper() {
    }

    public static VehicleDTO mapToDto(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setVinNumber(vehicle.getVinNumber());
        vehicleDTO.setMark(MarkMapper.mapToDto(vehicle.getMark()));
        vehicleDTO.setModel(ModelMapper.mapToDto(vehicle.getModel()));
        vehicleDTO.setOwner(OwnerMapper.mapToDto(vehicle.getOwner()));
        vehicleDTO.setChecked(vehicle.isChecked());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setHorsePower(vehicle.getHorsPower());
        vehicleDTO.setInsuranceExpiry(vehicle.getInsuranceExpiry());
        vehicleDTO.setPlateNumber(vehicle.getPlateNumber());
        vehicleDTO.setProductionYear(vehicle.getProductionYear());
        vehicleDTO.setTechInspectionExpiry(vehicle.getTechInspectionExpiry());
        return vehicleDTO;
    }

    public static VehicleDTO mapToSimpleDto(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setVinNumber(vehicle.getVinNumber());
        vehicleDTO.setMark(MarkMapper.mapToDto(vehicle.getMark()));
        vehicleDTO.setModel(ModelMapper.mapToDto(vehicle.getModel()));
        vehicleDTO.setChecked(vehicle.isChecked());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setHorsePower(vehicle.getHorsPower());
        vehicleDTO.setInsuranceExpiry(vehicle.getInsuranceExpiry());
        vehicleDTO.setPlateNumber(vehicle.getPlateNumber());
        vehicleDTO.setProductionYear(vehicle.getProductionYear());
        vehicleDTO.setTechInspectionExpiry(vehicle.getTechInspectionExpiry());
        return vehicleDTO;
    }

    public static Vehicle mapToEntityWithoutRelations(VehicleDTO vehicle) {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(vehicle.getId());
        vehicle1.setVinNumber(vehicle.getVinNumber());
        vehicle1.setChecked(vehicle.isChecked());
        vehicle1.setColor(vehicle.getColor());
        vehicle1.setHorsePower(vehicle.getHorsePower());
        vehicle1.setInsuranceExpiry(vehicle.getInsuranceExpiry());
        vehicle1.setPlateNumber(vehicle.getPlateNumber());
        vehicle1.setProductionYear(vehicle.getProductionYear());
        vehicle1.setTechInspectionExpiry(vehicle.getTechInspectionExpiry());
        vehicle1.setOwner(OwnerMapper.mapToEntityWithoutVehicles(vehicle.getOwner()));
        return vehicle1;
    }

    public static Vehicle mapToEntity(VehicleDTO vehicle) {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(vehicle.getId());
        vehicle1.setVinNumber(vehicle.getVinNumber());
        vehicle1.setChecked(vehicle.isChecked());
        vehicle1.setColor(vehicle.getColor());
        vehicle1.setHorsePower(vehicle.getHorsePower());
        vehicle1.setInsuranceExpiry(vehicle.getInsuranceExpiry());
        vehicle1.setPlateNumber(vehicle.getPlateNumber());
        vehicle1.setProductionYear(vehicle.getProductionYear());
        vehicle1.setTechInspectionExpiry(vehicle.getTechInspectionExpiry());
        vehicle1.setModel(ModelMapper.mapToEntity(vehicle.getModel()));
        vehicle1.setMark(MarkMapper.mapToEntity(vehicle.getMark()));
        vehicle1.setOwner(OwnerMapper.mapToEntityWithoutVehicles(vehicle.getOwner()));
        return vehicle1;
    }
}
