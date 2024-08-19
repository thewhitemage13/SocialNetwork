package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.SubscriptionClient;
import org.thewhitemage13.interfaces.FollowerValidationServiceInterface;

@Service
public class FollowerValidationService implements FollowerValidationServiceInterface {
    private final SubscriptionClient subscriptionClient;

    @Autowired
    public FollowerValidationService(SubscriptionClient subscriptionClient) {
        this.subscriptionClient = subscriptionClient;
    }

    @Override
    public Long countFollowersValidation(Long userId) {
        Long countFollowers;
        try {
            countFollowers = subscriptionClient.countFollowersByFollowingId(userId).getBody();
        } catch (Exception e) {
            countFollowers = 0L;
        }
        return countFollowers;
    }

    @Override
    public Long countFollowingValidation(Long userId) {
        Long countFollowing;
        try {
            countFollowing = subscriptionClient.countFollowingByFollower(userId).getBody();
        } catch (Exception e) {
            countFollowing = 0L;
        }
        return countFollowing;
    }
}
