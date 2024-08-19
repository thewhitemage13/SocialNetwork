package org.thewhitemage13.service;

import org.springframework.stereotype.Service;
import org.thewhitemage13.dto.PostDTO;
import org.thewhitemage13.interfaces.ValidationServiceInterface;

@Service
public class ValidationService implements ValidationServiceInterface {
    private final CommentValidationService commentValidationService;
    private final LikeValidationService likeValidationService;
    private final MediaValidationService mediaValidationService;
    private final UserValidationService userValidationService;

    public ValidationService(CommentValidationService commentValidationService, LikeValidationService likeValidationService, MediaValidationService mediaValidationService, UserValidationService userValidationService) {
        this.commentValidationService = commentValidationService;
        this.likeValidationService = likeValidationService;
        this.mediaValidationService = mediaValidationService;
        this.userValidationService = userValidationService;
    }

    @Override
    public Long validateComment(Long postId) {
        return commentValidationService.countCommentValidation(postId);
    }

    @Override
    public Long validateLike(Long postId) {
        return likeValidationService.countLikeValidation(postId);
    }

    @Override
    public void validateMedia(PostDTO postDTO) {
        mediaValidationService.isCreateMedia(postDTO);
    }

    @Override
    public void validateUser(PostDTO postDTO) {
        userValidationService.validateUser(postDTO);
    }

}
