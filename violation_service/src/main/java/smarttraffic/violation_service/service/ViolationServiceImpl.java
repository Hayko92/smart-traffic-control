package smarttraffic.violation_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.entity.Owner;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.repository.ViolationRepository;
import smarttraffic.violation_service.util.JwtTokenUtil;

import java.util.List;
import java.util.Optional;

@Service
public class ViolationServiceImpl implements ViolationService {
    @Value("${vehicleService}")
    private String vehicleServiceUrl;

    @Value("${notificationService}")
    private String notificationServiceUrl;
    @Autowired
    private ViolationRepository violationRepository;

    @Override
    public List<Violation> getAllViolations() {
        return violationRepository.findAll();
    }

    @Override
    public void save(Violation violation) {
        violationRepository.save(violation);
    }

    @Override
    public Optional<Violation> getByNumber(String number) {
        return violationRepository.findById(number);
    }

    @Override
    public void delete(Violation violation) {
        violationRepository.delete(violation);
    }

    @Override
    public List<Violation> getAllByNumber(String number) {
        return violationRepository.getAllByNumber(number);
    }

    @Override
    public List<Violation> getAllByOwnerID(Long ownerID) {
        return violationRepository.getAllByOwnerId(ownerID);
    }

    @Override
    public void reduceOwnerPoints(Owner owner, String token) {
        RestTemplate restTemplate = new RestTemplate();
        if (owner != null) {

            HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
            HttpEntity<Owner> httpEntity = new HttpEntity<>(owner, headers);
            HttpEntity  httpEntity1 = new HttpEntity(  headers);
            restTemplate.exchange(vehicleServiceUrl + "/owner/" + owner.getId(), HttpMethod.POST, httpEntity, Owner.class);
            if (owner.getPoints() == 1) {
                owner.getRedusedPoint();
                restTemplate.exchange(notificationServiceUrl + "/patrol/owner/" + owner.getId(),HttpMethod.GET,httpEntity1, Void.class);
            }
        }

    }
}