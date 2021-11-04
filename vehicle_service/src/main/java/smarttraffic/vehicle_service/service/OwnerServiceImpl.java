package smarttraffic.vehicle_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import smarttraffic.vehicle_service.entity.Owner;
import smarttraffic.vehicle_service.repository.OwnerRepository;

public class OwnerServiceImpl implements OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    @Override
    public Owner getById(long id) {
        return ownerRepository.getById(id);
    }

    @Override
    public void create(Owner owner) {
        ownerRepository.save(owner);
    }

    @Override
    public void update(Owner owner) {
        ownerRepository.save(owner);
    }

    @Override
    public void delete(long id) {
        ownerRepository.getById(id);
    }
}
