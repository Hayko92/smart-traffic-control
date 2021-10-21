package smarttraffic.detectors_analyzer.service;

import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.entity.InsuranceViolation;

@Service
public interface CaptureService {
     void save(Capture capture);

     boolean hasValidInsurance(Capture capture);

     boolean hasValidTechInspection(Capture capture);

     void setStatusChecked(Capture capture);

     void createViolation(InsuranceViolation insuranceViolation, Capture capture);
}
