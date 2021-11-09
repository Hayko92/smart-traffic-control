package smarttraffic.violations_analyzer_service.service;

import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.Instant;

@Service
public interface ViolationsAnalyzerService {
    void startAnalyze(Instant from, Instant to) throws FileNotFoundException, DocumentException;
}
