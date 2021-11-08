package smarttraffic.violations_analyzer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violations_analyzer_service.model.DetectorDto;
import smarttraffic.violations_analyzer_service.model.VehicleDTO;
import smarttraffic.violations_analyzer_service.service.VehicleService;
import smarttraffic.violations_analyzer_service.util.JwtTokenUtil;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/violations_analyzer_service")
public class ViolationsAnalyzerController {

    @Autowired
    VehicleService vehicleService;


    @Value("${vehicleService}")
    private String vehicleServiceURL;

    @Value("${cameraImitationServise}")
    private String detectorImitationUrl;
    @GetMapping("/all_time")
    public void collectdata (@RequestHeader(name = "AUTHORIZATION") String token,
                             @RequestParam Instant from,
                             @RequestParam Instant to) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<List<VehicleDTO>> allvehicles = restTemplate.exchange(vehicleServiceURL + "/all", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });

        ResponseEntity<List<DetectorDto>> alldetectors = restTemplate.exchange(detectorImitationUrl + "/all", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });
        List<VehicleDTO> allVehicleslist = allvehicles.getBody();
        vehicleService.saveAll(allVehicleslist);

        List<DetectorDto> alldetectorsList = alldetectors.getBody();

    }

    //time in format 12.12.2012
    @GetMapping("/from_time/{timeFrom}")
    public void analyzeFrom(@PathVariable String timeFrom, @RequestHeader String token) {

    }

}
