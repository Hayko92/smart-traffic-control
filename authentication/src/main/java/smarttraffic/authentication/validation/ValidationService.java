package smarttraffic.authentication.validation;

import org.springframework.stereotype.Service;
import smarttraffic.authentication.entity.Role;
import smarttraffic.authentication.entity.User;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ValidationService {
    private  final Validator validator;

    ValidationService(Validator validator) {
        this.validator = validator;
    }

}
