package smarttraffic.vehicle_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.vehicle_service.entity.Owner;
import smarttraffic.vehicle_service.entity.Vehicle;

@Service
public interface OwnerService {
    Owner getById(long id);

    void save (Owner owner);

    void delete(Owner owner);

    void update(Owner owner);

    void delete(long id);
}
