package org.thewhitemage13.interfaces;

import org.thewhitemage13.dto.CreateLikeComment;
import org.thewhitemage13.dto.CreateLikePost;
import org.thewhitemage13.exception.LikeNotFoundException;

public interface LikeServiceInterface {
     void deleteAllByCommentId(Long commentId) throws LikeNotFoundException;
     Long getPostLikeCount(Long postId);
     void deleteAllByPostId(Long postId) throws LikeNotFoundException;
     void deleteAllByUserId(Long userId) throws LikeNotFoundException;
     void postLike(CreateLikePost createLikePost);
     void commentLike(CreateLikeComment createLikeComment);
     void deleteLike(Long likeId) throws LikeNotFoundException;
     Long showPostLikeSum(Long postId);
     Long showCommentLikeSum(Long commentId);
}
