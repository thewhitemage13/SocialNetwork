package org.thewhitemage13.exception;

public class UsernameIsBusyException extends RuntimeException {
    public UsernameIsBusyException() {
        super();
    }

    public UsernameIsBusyException(String message) {
        super(message);
    }

    public UsernameIsBusyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameIsBusyException(Throwable cause) {
        super(cause);
    }
}
