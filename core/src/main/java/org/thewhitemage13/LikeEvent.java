package org.thewhitemage13;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LikeEvent implements Serializable {
    private Long likeId;
    private Long userId;
    private Long postId;
    private Long commentId;
    private LocalDateTime createdAt;

    public LikeEvent() {
    }

    public LikeEvent(Long likeId, Long userId, Long postId, Long commentId, LocalDateTime createdAt) {
        this.likeId = likeId;
        this.userId = userId;
        this.postId = postId;
        this.commentId = commentId;
        this.createdAt = createdAt;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "LikeEvent{" +
                "likeId=" + likeId +
                ", userId=" + userId +
                ", postId=" + postId +
                ", commentId=" + commentId +
                ", createdAt=" + createdAt +
                '}';
    }
}
