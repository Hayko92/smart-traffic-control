package smarttraffic.authentication.validating;

import org.springframework.stereotype.Service;
import smarttraffic.authentication.entity.Role;
import smarttraffic.authentication.entity.User;

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

    void validateUserWithInjectedValidator(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    void validateInputWithInjectedValidator(Role role) {
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
