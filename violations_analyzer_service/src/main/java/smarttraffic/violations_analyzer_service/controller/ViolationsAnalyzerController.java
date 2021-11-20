package smarttraffic.violations_analyzer_service.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarttraffic.violations_analyzer_service.security.DataCollector;
import smarttraffic.violations_analyzer_service.service.ViolationsAnalyzerService;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.Instant;

@RestController
@RequestMapping("api/violations_analyzer_service")
public class ViolationsAnalyzerController {

    private final DataCollector dataCollector;
    ViolationsAnalyzerService violationsAnalyzerService;

    @Autowired
    public ViolationsAnalyzerController(DataCollector dataCollector, ViolationsAnalyzerService violationsAnalyzerService) {
        this.dataCollector = dataCollector;
        this.violationsAnalyzerService = violationsAnalyzerService;
    }

    @GetMapping(value = "/all_time", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> collectdata(@RequestHeader(name = "AUTHORIZATION") String token,
                                                           @RequestParam(required = false) Instant from,
                                                           @RequestParam(required = false) Instant to) throws DocumentException, FileNotFoundException {

        dataCollector.collectData(token);
        Path path = violationsAnalyzerService.startAnalyze(from, to);
        return violationsAnalyzerService.downloadAnalyzeResult(path);
    }

}
