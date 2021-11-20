package smarttraffic.vehicle_service.exception;

import org.springframework.http.HttpStatus;

public class SmartTrafficControlException extends RuntimeException {

    public SmartTrafficControlException(String message, HttpStatus status) {
        super(message);
    }
}
