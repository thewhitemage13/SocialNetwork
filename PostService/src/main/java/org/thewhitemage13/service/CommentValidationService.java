package org.thewhitemage13.service;

import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.CommentClient;
import org.thewhitemage13.interfaces.CommentValidationServiceInterface;

@Service
public class CommentValidationService implements CommentValidationServiceInterface {
    private final CommentClient commentClient;

    public CommentValidationService(CommentClient commentClient) {
        this.commentClient = commentClient;
    }

    @Override
    public Long countCommentValidation(Long postId) {
        Long comments;
        try {
            comments = commentClient.getCommentCountByPostId(postId).getBody();
        } catch (Exception e) {
            comments = 0L;
        }
        return comments;
    }
}
