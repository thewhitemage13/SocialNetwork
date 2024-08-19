package org.thewhitemage13.interfaces;

import org.thewhitemage13.dao.SubscriptionDAO;
import org.thewhitemage13.dto.UserSubscriptionDTO;

import java.util.List;

public interface SubscriptionInterface {
    void createSubscription(Long followerId, Long followingId);
    void deleteSubscription(Long followerId, Long followingId);
    List<SubscriptionDAO> getAllFollowingByFollowerId(Long followerId);
    List<SubscriptionDAO> getAllFollowingByFollowingId(Long followingId);
    boolean followingVerification(Long followerId, Long followingId);
    List<UserSubscriptionDTO> getFollowers(Long userId);
    List<UserSubscriptionDTO> getFollowing(Long userId);
    Long countFollowersByFollowingId(Long followingId);
    Long countFollowingByFollower(Long followerId);
    void deleteFollowersById(Long followerId);
    void deleteFollowingById(Long followingId);

}
