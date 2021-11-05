package smarttraffic.vehicle_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.vehicle_service.dto.OwnerDTO;
import smarttraffic.vehicle_service.entity.Owner;
import smarttraffic.vehicle_service.mapper.OwnerMapper;
import smarttraffic.vehicle_service.repository.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    @Override
    public OwnerDTO getById(long id) {
        Owner owner = ownerRepository.getById(id);
        return OwnerMapper.mapToDto(owner);
    }

    @Override
    public long save(OwnerDTO owner) {
        return ownerRepository.save(OwnerMapper.mapToEntityWithoutVehicles(owner)).getId();
    }

    @Override
    public void delete(OwnerDTO owner) {
        Owner owner1 = OwnerMapper.mapToEntity(owner);
        ownerRepository.delete(owner1);
    }

    @Override
    public void update(OwnerDTO owner) {
        Owner owner1 = OwnerMapper.mapToEntity(owner);
        ownerRepository.save(owner1);
    }

    @Override
    public void delete(long id) {
        ownerRepository.getById(id);
    }

    @Override
    public OwnerDTO getOwnerByMail(String email) {
        Owner owner = ownerRepository.getByOwnerContact_EmailAddress(email);
        OwnerDTO ownerDTO = null;
        if (owner != null) ownerDTO = OwnerMapper.mapToDto(owner);
        return ownerDTO;
    }
}
