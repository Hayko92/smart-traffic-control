package smarttraffic.violations_analyzer_service.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smarttraffic.violations_analyzer_service.model.Capture;
import smarttraffic.violations_analyzer_service.model.Detector;
import smarttraffic.violations_analyzer_service.model.Violation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViolationsAnalyzerServiceImpl implements ViolationsAnalyzerService {

    ViolationService violationService;
    CaptureService captureService;
    VehicleService vehicleService;
    DetectorService detectorService;

    @Autowired
    public ViolationsAnalyzerServiceImpl(ViolationService violationService,
                                         CaptureService captureService,
                                         VehicleService vehicleService,
                                         DetectorService detectorService) {
        this.violationService = violationService;
        this.captureService = captureService;
        this.vehicleService = vehicleService;
        this.detectorService = detectorService;
    }

    @Override
    public Path startAnalyze(Instant from, Instant to) throws FileNotFoundException, DocumentException {
        Path path = Paths.get("classpath:analyze.pdf");
        Document document = openDocument(path.toAbsolutePath());
        Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
        List<Violation> violations = getViolations(from, to);
        List<Capture> captures = getCaptures(from, to);
        List<Detector> detectors = getDetectors();
        addTextToDocument("========Smart traffic control system analytics======", document, font);
        addTextToDocument(String.format("Count of detectors: %s", detectors.size()), document, font);
        addTextToDocument(String.format("Count of captures: %s", captures.size()), document, font);
        addTextToDocument(String.format("Count of violations: %s", violations.size()), document, font);
        addTextToDocument("====================================================", document, font);
        addTextToDocument(String.format("Percent of violations in captures: %s %%", getPercentOfViolationsIncaptures(captures, violations)), document, font);
        addTextToDocument(String.format("Percent of SPEED type violations: %s %%", getViolationPercentByTyoe("SPEED", violations)), document, font);
        addTextToDocument(String.format("Percent of INS type violations: %s %%", getViolationPercentByTyoe("INS", violations)), document, font);
        addTextToDocument(String.format("Percent of TECH type violations: %s %%", getViolationPercentByTyoe("TECH", violations)), document, font);
        addTextToDocument("=============analyze results per detectors==========", document, font);
        startAnalyzeByDetectors(document, font, captures, detectors, violations);
        document.close();
        return path;

    }

    private void startAnalyzeByDetectors(Document document, Font font, List<Capture> captures, List<Detector> detectors, List<Violation> violations) throws DocumentException {

        for (Detector detector : detectors) {
            List<Capture> capturesOfConcretDetector = captures
                    .stream()
                    .filter(e -> e.getPlace().equals(detector.getPlace()))
                    .collect(Collectors.toList());
            List<Violation> violationsOfConcretDetector = violations
                    .stream()
                    .filter(e -> e.getPlace().equals(detector.getPlace()))
                    .collect(Collectors.toList());
            addTextToDocument(String.format("============-detectors place : %s==========", detector.getPlace()), document, font);
            addTextToDocument(String.format("Count of captures: %s", capturesOfConcretDetector.size()), document, font);
            addTextToDocument(String.format("Count of violations: %s", violationsOfConcretDetector.size()), document, font);
            addTextToDocument("====================================================", document, font);
            addTextToDocument(String.format("Percent of violations in captures: %s %%", getPercentOfViolationsIncaptures(capturesOfConcretDetector, violationsOfConcretDetector)), document, font);
            addTextToDocument(String.format("Percent of SPEED type violations: %s %%", getViolationPercentByTyoe("SPEED", violationsOfConcretDetector)), document, font);
            addTextToDocument(String.format("Percent of INS type violations: %s %%", getViolationPercentByTyoe("INS", violationsOfConcretDetector)), document, font);
            addTextToDocument(String.format("Percent of TECH type violations: %s %%", getViolationPercentByTyoe("TECH", violationsOfConcretDetector)), document, font);
        }
    }

    public ResponseEntity<InputStreamResource> downloadAnalyzeResult(Path path) throws FileNotFoundException {
        File file = path.toFile();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(file.length()) //
                .body(resource);
    }

    private double getPercentOfViolationsIncaptures(List<Capture> captures, List<Violation> violations) {
        double res = 0;
        if (captures != null && violations != null) {
            res = ((double) violations.size()) * 100 / captures.size();
        }
        return Math.floor(res * 100) / 100;
    }

    private List<Detector> getDetectors() {
        return detectorService.findAll();
    }

    private List<Capture> getCaptures(Instant from, Instant to) {
        return captureService.setCaptures(from, to);
    }

    private double getViolationPercentByTyoe(String type, List<Violation> violations) {
        double res = 0;
        long count = violations.stream()
                .filter(e -> e.getType().equals(type)).count();
        res = (double) count * 100 / violations.size();
        return Math.floor(res * 100) / 100;
    }

    private Document openDocument(Path path) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(path.toFile()));
        document.open();
        return document;
    }

    private void addTextToDocument(String text, Document document, Font font) throws DocumentException {
        Chunk chunk = new Chunk(text, font);
        document.add(chunk);
        document.add(Paragraph.getInstance("\n"));
    }

    private List<Violation> getViolations(Instant from, Instant to) {
        return violationService.getViolations(from, to);
    }

}
