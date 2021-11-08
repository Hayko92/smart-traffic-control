package smarttraffic.authentication.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import smarttraffic.authentication.errorResponseModel.SmartTrafficControlApiError;
import smarttraffic.authentication.errorResponseModel.SmartTrafficControlApiSubError;
import smarttraffic.authentication.exception.SmartTrafficControlException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class SmartTrafficAppExceptionHanlder extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SmartTrafficControlException.class)
    protected ResponseEntity<Object> handleApplicationExceptionFound(
            SmartTrafficControlException ex) {
        SmartTrafficControlApiError apiError = new SmartTrafficControlApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(ex.getStatus());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAnyException(
            Exception ex) {
        SmartTrafficControlApiError apiError = new SmartTrafficControlApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        SmartTrafficControlApiError error = new SmartTrafficControlApiError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(e.getMessage());
        e.getConstraintViolations()
                .stream()
                .forEach(er -> error.getSubErrors().add(new SmartTrafficControlApiSubError(er.getPropertyPath().toString(), er.getMessage())));
        return buildResponseEntity(error);
    }

    private ResponseEntity<Object> buildResponseEntity(SmartTrafficControlApiError error) {
        return new ResponseEntity<Object>(error, error.getStatus());
    }
}
