package org.thewhitemage13.dto;

public class UserSubscriptionDTO {
    private Long userId;
    private String username;

    public UserSubscriptionDTO() {
    }

    public UserSubscriptionDTO(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserSubscriptionDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
