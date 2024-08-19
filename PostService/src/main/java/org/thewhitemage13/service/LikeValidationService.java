package org.thewhitemage13.service;

import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.LikeClient;
import org.thewhitemage13.interfaces.LikeValidationServiceInterface;

@Service
public class LikeValidationService implements LikeValidationServiceInterface {
    private final LikeClient likeClient;

    public LikeValidationService(LikeClient likeClient) {
        this.likeClient = likeClient;
    }

    @Override
    public Long countLikeValidation(Long postId) {
        Long likes;
        try {
            likes = likeClient.getPostLikeCount(postId).getBody();
        } catch (Exception e) {
            likes = 0L;
        }
        return likes;
    }
}
