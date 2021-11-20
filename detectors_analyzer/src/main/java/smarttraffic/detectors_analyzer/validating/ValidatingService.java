package smarttraffic.detectors_analyzer.validating;

import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.entity.Capture;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ValidatingService {
    private final Validator validator;

    ValidatingService(Validator validator) {
        this.validator = validator;
    }

    void validateCaptureWithInjectedValidator(Capture capture) {
        Set<ConstraintViolation<Capture>> violations = validator.validate(capture);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
