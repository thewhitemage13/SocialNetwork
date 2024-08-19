package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.UserClient;
import org.thewhitemage13.exception.UserNotFoundException;
import org.thewhitemage13.interfaces.ValidationServiceInterface;
import org.thewhitemage13.repository.SubscriptionRepository;

@Service
public class ValidationService implements ValidationServiceInterface {
    private final UserClient userClient;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public ValidationService(UserClient userClient, SubscriptionRepository subscriptionRepository) {
        this.userClient = userClient;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void validateUser(Long userId) {
        Boolean isCreate;

        ResponseEntity<Boolean> userIsCreate = userClient.userVerification(userId);
        isCreate = userIsCreate.getBody();

        if (Boolean.FALSE.equals(isCreate)) {
            throw new UserNotFoundException("User with id = %s not found".formatted(userId));
        }
    }

    @Override
    public void validateSubscriptionVerification(Long followerId, Long followingId) {

        if (followerId.equals(followingId)) {
            throw new RuntimeException("The id's don't have to match");
        }

        if (subscriptionRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            throw new RuntimeException("You are already subscribed to this user");
        }

    }
}
