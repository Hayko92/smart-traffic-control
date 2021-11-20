package smarttraffic.authentication.exceptionHandler;


import org.springframework.http.HttpStatus;
import smarttraffic.authentication.errorResponseModel.ResponseError;

public class SmartTrafficControlApiError extends ResponseError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public SmartTrafficControlApiError(HttpStatus status, String message, Throwable ex, String object, String field, Object rejectedValue, String message1) {
        super(status, message, ex);
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message1;
    }

    public SmartTrafficControlApiError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public SmartTrafficControlApiError(HttpStatus status) {
        super(status);
    }

    public SmartTrafficControlApiError(HttpStatus status, Throwable ex) {
        super(status, ex);
    }

    public SmartTrafficControlApiError(HttpStatus status, String message, Throwable ex) {
        super(status, message, ex);
    }

    public SmartTrafficControlApiError() {
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
