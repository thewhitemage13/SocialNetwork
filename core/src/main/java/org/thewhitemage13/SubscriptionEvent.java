package org.thewhitemage13;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SubscriptionEvent implements Serializable {
    private Long subscriptionId;
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;

    public SubscriptionEvent() {
    }

    public SubscriptionEvent(Long subscriptionId, Long followerId, Long followingId, LocalDateTime createdAt) {
        this.subscriptionId = subscriptionId;
        this.followerId = followerId;
        this.followingId = followingId;
        this.createdAt = createdAt;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SubscriptionEvent{" +
                "subscriptionId=" + subscriptionId +
                ", followerId=" + followerId +
                ", followingId=" + followingId +
                ", createdAt=" + createdAt +
                '}';
    }
}
