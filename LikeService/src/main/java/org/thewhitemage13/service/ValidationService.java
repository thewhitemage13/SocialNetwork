package org.thewhitemage13.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.CommentClient;
import org.thewhitemage13.clients.PostClient;
import org.thewhitemage13.clients.UserClient;
import org.thewhitemage13.dto.CreateLikeComment;
import org.thewhitemage13.dto.CreateLikePost;
import org.thewhitemage13.exception.CommentNotFoundException;
import org.thewhitemage13.exception.PostNotFoundException;
import org.thewhitemage13.exception.UserNotFoundException;
import org.thewhitemage13.interfaces.ValidationServiceInterface;

@Service
public class ValidationService implements ValidationServiceInterface {
    private final CommentClient commentClient;
    private final PostClient postClient;
    private final UserClient userClient;

    public ValidationService(CommentClient commentClient, PostClient postClient, UserClient userClient) {
        this.commentClient = commentClient;
        this.postClient = postClient;
        this.userClient = userClient;
    }

    @Override
    public void validatePostLike(CreateLikePost createLikePost) {
        Boolean status;

        ResponseEntity<Boolean> isCreatedPost = postClient.postVerification(createLikePost.getPostId());
        status = isCreatedPost.getBody();

        if(Boolean.FALSE.equals(status)) {
            throw new PostNotFoundException("Post with id = %s not found".formatted(createLikePost.getPostId()));
        }

        ResponseEntity<Boolean> isCreatedUser = userClient.userVerification(createLikePost.getUserId());
        status = isCreatedUser.getBody();

        if(Boolean.FALSE.equals(status)) {
            throw new UserNotFoundException("User with id = %s not found".formatted(createLikePost.getUserId()));
        }
    }

    @Override
    public void validateCommentLike(CreateLikeComment createLikeComment) {
        Boolean status;

        ResponseEntity<Boolean> isCreatedComment = commentClient.commentVerification(createLikeComment.getCommentId());
        status = isCreatedComment.getBody();

        if(Boolean.FALSE.equals(status)) {
            throw new CommentNotFoundException("Comment with id = %s not found".formatted(createLikeComment.getCommentId()));
        }

        ResponseEntity<Boolean> isCreatedUser = userClient.userVerification(createLikeComment.getUserId());
        status = isCreatedUser.getBody();

        if(Boolean.FALSE.equals(status)) {
            throw new UserNotFoundException("User with id = %s not found".formatted(createLikeComment.getUserId()));
        }
    }
}
