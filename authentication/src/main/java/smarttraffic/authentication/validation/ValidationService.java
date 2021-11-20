package smarttraffic.authentication.validation;

import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class ValidationService {
    private final Validator validator;

    ValidationService(Validator validator) {
        this.validator = validator;
    }

}
