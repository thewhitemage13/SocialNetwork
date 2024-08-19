package org.thewhitemage13.exception;

public class MediaNotFoundException extends Exception {

    public MediaNotFoundException() {
        super();
    }

    public MediaNotFoundException(String message) {
        super(message);
    }

    public MediaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MediaNotFoundException(Throwable cause) {
        super(cause);
    }

}
