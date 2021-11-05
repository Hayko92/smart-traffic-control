package smarttraffic.vehicle_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.vehicle_service.dto.OwnerDTO;

@Service
public interface OwnerService {
    OwnerDTO getById(long id);

    long save(OwnerDTO owner);

    void delete(OwnerDTO owner);

    void update(OwnerDTO owner);

    void delete(long id);

    OwnerDTO getOwnerByMail(String email);
}
