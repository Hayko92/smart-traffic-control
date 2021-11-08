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
import smarttraffic.violations_analyzer_service.model.VehicleDTO;
import smarttraffic.violations_analyzer_service.service.VehicleService;
import smarttraffic.violations_analyzer_service.util.JwtTokenUtil;

import java.util.List;

@RestController
@RequestMapping("api/violations_analyzer_service")
public class ViolationsAnalyzerController {

    @Autowired
    VehicleService vehicleService;
    @Value("${vehicleService}")
    private String vehicleServiceURL;

    @GetMapping("/all_time")
    public void analyze(@RequestHeader(name = "AUTHORIZATION") String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<List<VehicleDTO>> allvehicles = restTemplate.exchange(vehicleServiceURL + "/all", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });
        List<VehicleDTO> allVehicles = allvehicles.getBody();
        vehicleService.saveAll(allVehicles);

    }

    //time in format 12.12.2012
    @GetMapping("/from_time/{timeFrom}")
    public void analyzeFrom(@PathVariable String timeFrom, @RequestHeader String token) {

    }

}
