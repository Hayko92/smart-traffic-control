package smarttraffic.violations_analyzer_service.service;

import com.itextpdf.text.DocumentException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.Instant;

@Service
public interface ViolationsAnalyzerService {

    Path startAnalyze(Instant from, Instant to) throws FileNotFoundException, DocumentException;

    ResponseEntity<InputStreamResource> downloadAnalyzeResult(Path path) throws FileNotFoundException;
}
