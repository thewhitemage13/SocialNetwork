package org.thewhitemage13.exception;

public class LikeNotFoundException extends Exception{

    public LikeNotFoundException() {
        super();
    }

    public LikeNotFoundException(String message) {
        super(message);
    }

    public LikeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LikeNotFoundException(Throwable cause) {
        super(cause);
    }
}
