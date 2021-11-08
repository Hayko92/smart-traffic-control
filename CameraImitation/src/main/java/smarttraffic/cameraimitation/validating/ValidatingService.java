package smarttraffic.cameraimitation.validating;

import org.springframework.stereotype.Service;
import smarttraffic.cameraimitation.entity.Detector;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ValidatingService {
    private Validator validator;

    ValidatingService(Validator validator) {
        this.validator = validator;
    }

    void validateInputWithInjectedValidator(Detector detector) {
        Set<ConstraintViolation<Detector>> violations = validator.validate(detector);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
