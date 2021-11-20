package smarttraffic.violation_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import smarttraffic.violation_service.dto.CaptureDTO;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.service.ViolationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/violation-service")
public class ViolationController {
    @Value("${sqs.url}")
    String sqsURL;

    @Value("${sqs.region}")
    String region;

    @Value("${access.key.id}")
    String accessKey;




    @Autowired
    private ViolationService violationService;


    @GetMapping("/all")
    public List<ViolationDTO> getAllViolations(@RequestHeader(name = "AUTHORIZATION") String token) {
        return violationService.findAll();
    }

    @GetMapping("/violation/{id}")
    public  ViolationDTO getViolationById(@PathVariable long id) {
        return violationService.findById(id);
    }

    @PostMapping("/speed")
    public void createSpeedViolationDTO(@RequestBody Map<String, Integer> info, @RequestHeader(name = "AUTHORIZATION") String token) throws JsonProcessingException {
       violationService.createSpeedViolation(info,token);
        extracted(info, token);
    }


    private void extracted(Map<String, Integer> info, String token) throws JsonProcessingException {

    }


    @PostMapping
    public void createViolation(@RequestBody Map<String, CaptureDTO> body, @RequestHeader(name = "AUTHORIZATION") String token) throws JsonProcessingException {
       violationService.createViolation(body,token);
        extracted1(body, token);
    }

    private void extracted1(Map<String, CaptureDTO> body, String token) {

    }



    @GetMapping("/platenumber/{vehiclenumber}")
    public List<ViolationDTO> sendViolationsByplatenumber(@PathVariable String vehiclenumber) {
        return violationService.getAllByNumber(vehiclenumber);
    }

    @GetMapping("/ownerID/{ownerID}")
    public List<ViolationDTO> getAllViolationsByOwnerId(@RequestBody Long ownerID) {
        return violationService.getAllByOwnerID(ownerID);
    }

}