package smarttraffic.authentication.exception;

import org.springframework.http.HttpStatus;

public class SmartTrafficControlException extends RuntimeException {
    private HttpStatus status;

    public SmartTrafficControlException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
