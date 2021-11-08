package smarttraffic.authentication.errorResponseModel;


import org.springframework.http.HttpStatus;

public class SmartTrafficControlApiSubError extends SmartTrafficControlApiError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public SmartTrafficControlApiSubError(HttpStatus status, String message, Throwable ex, String object, String field, Object rejectedValue, String message1) {
        super(status, message, ex);
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message1;
    }

    public SmartTrafficControlApiSubError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public SmartTrafficControlApiSubError(HttpStatus status) {
        super(status);
    }

    public SmartTrafficControlApiSubError(HttpStatus status, Throwable ex) {
        super(status, ex);
    }

    public SmartTrafficControlApiSubError(HttpStatus status, String message, Throwable ex) {
        super(status, message, ex);
    }

    public SmartTrafficControlApiSubError() {
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
