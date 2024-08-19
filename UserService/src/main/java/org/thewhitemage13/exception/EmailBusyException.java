package org.thewhitemage13.exception;

public class EmailBusyException extends RuntimeException {
    public EmailBusyException() {
        super();
    }

    public EmailBusyException(String message) {
        super(message);
    }

    public EmailBusyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailBusyException(Throwable cause) {
        super(cause);
    }
}
