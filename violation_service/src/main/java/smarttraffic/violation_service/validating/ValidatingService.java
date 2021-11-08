package smarttraffic.violation_service.validating;

import org.springframework.stereotype.Service;
import smarttraffic.violation_service.entity.*;

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

    void validateAddressWithInjectedValidator(Address address) {
        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    void validateOwnerWithInjectedValidator(Owner owner) {
        Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    void validateOwnerContactWithInjectedValidator(OwnerContact ownerContact) {
        Set<ConstraintViolation<OwnerContact>> violations = validator.validate(ownerContact);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    void validateVehicletWithInjectedValidator(Vehicle vehicle) {
        Set<ConstraintViolation<Vehicle>> violations = validator.validate(vehicle);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    void validateVehicleMarkWithInjectedValidator(VehicleMark vehicleMark) {
        Set<ConstraintViolation<VehicleMark>> violations = validator.validate(vehicleMark);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    void validateVehicleModelWithInjectedValidator(VehicleModel vehicleModel) {
        Set<ConstraintViolation<VehicleModel>> violations = validator.validate(vehicleModel);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    void validateViolationWithInjectedValidator(Violation violation) {
        Set<ConstraintViolation<Violation>> violations = validator.validate(violation);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
