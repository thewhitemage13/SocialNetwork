package org.thewhitemage13.interfaces;

import org.thewhitemage13.dto.CreateLikeComment;
import org.thewhitemage13.dto.CreateLikePost;

public interface ValidationServiceInterface {
    void validatePostLike(CreateLikePost createLikePost);
    void validateCommentLike(CreateLikeComment createLikeComment);
}
