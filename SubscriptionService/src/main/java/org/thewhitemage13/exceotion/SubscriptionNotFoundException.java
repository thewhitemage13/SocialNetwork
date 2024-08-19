package org.thewhitemage13.exceotion;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException() {
        super();
    }

    public SubscriptionNotFoundException(String message) {
        super(message);
    }

    public SubscriptionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionNotFoundException(Throwable cause) {
        super(cause);
    }
}
