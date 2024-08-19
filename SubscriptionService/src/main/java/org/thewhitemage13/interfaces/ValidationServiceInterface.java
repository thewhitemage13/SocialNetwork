package org.thewhitemage13.interfaces;

public interface ValidationServiceInterface {
    void validateUser(Long userId);
    void validateSubscriptionVerification(Long followerId, Long followingId);
}
