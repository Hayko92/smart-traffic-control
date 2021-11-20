package smarttraffic.cameraimitation.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import smarttraffic.cameraimitation.errorResponseModel.ResponseError;
import smarttraffic.cameraimitation.exception.SmartTrafficControlException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class SmartTrafficAppExceptionHanlder extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SmartTrafficControlException.class)
    protected ResponseEntity<Object> handleApplicationExceptionFound(
            SmartTrafficControlException ex) {
        ResponseError apiError = new ResponseError();
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAnyException(
            Exception ex) {
        ResponseError apiError = new ResponseError();
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        ResponseError error = new ResponseError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(e.getMessage());
        e.getConstraintViolations()
                .stream()
                .forEach(er -> error.getSubErrors().add(new SmartTrafficControlApiError(er.getPropertyPath().toString(), er.getMessage())));
        return buildResponseEntity(error);
    }

    private ResponseEntity<Object> buildResponseEntity(ResponseError error) {
        return new ResponseEntity<Object>(error, error.getStatus());
    }
}
