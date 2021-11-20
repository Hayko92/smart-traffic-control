package smarttraffic.violation_service.errorResponseModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import smarttraffic.violation_service.exceptionHandler.SmartTrafficControlApiError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<SmartTrafficControlApiError> subErrors = new ArrayList<>();

    public ResponseError() {
        timestamp = LocalDateTime.now();
    }

    public ResponseError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ResponseError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ResponseError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public List<SmartTrafficControlApiError> getSubErrors() {
        if (subErrors == null) subErrors = new ArrayList<>();
        return subErrors;
    }

    public void setSubErrors(List<SmartTrafficControlApiError> subErrors) {
        this.subErrors = subErrors;
    }
}