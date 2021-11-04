package smarttraffic.vehicle_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.vehicle_service.entity.Owner;

@Service
public interface OwnerService {
    Owner getById(long id);

    void create(Owner owner);

    void update(Owner owner);

    void delete(long id);
}
