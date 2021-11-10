package smarttraffic.violations_analyzer_service.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.violations_analyzer_service.model.Capture;
import smarttraffic.violations_analyzer_service.model.Detector;
import smarttraffic.violations_analyzer_service.model.Violation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Instant;
import java.util.List;

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
    public void startAnalyze(Instant from, Instant to) throws FileNotFoundException, DocumentException {
        Document document = openDocument();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        List<Violation> violations = getViolations(from, to);
        List<Capture> captures = getCaptures(from, to);
        List<Detector> detectors = getDetectors();
        addTextToDocument("========Smart traffic control system analytics======", document, font);
        addTextToDocument(String.format("Count of detectors: %s", detectors.size()), document, font);
        addTextToDocument(String.format("Count of captures: %s", captures.size()), document, font);
        addTextToDocument(String.format("Count of violations: %s", violations.size()), document, font);
        addTextToDocument("====================================================", document, font);
        addTextToDocument(String.format("Percent of violations in captures: %s %%", getPercentOfViolationsIncaptures(captures, violations)), document, font);
        document.close();
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

    private Document openDocument() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("analyze.pdf"));
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
