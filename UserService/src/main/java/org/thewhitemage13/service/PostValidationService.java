package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.PostClient;
import org.thewhitemage13.interfaces.PostValidationServiceInterface;

@Service
public class PostValidationService implements PostValidationServiceInterface {
    private final PostClient postClient;

    @Autowired
    public PostValidationService(PostClient postClient) {
        this.postClient = postClient;
    }

    @Override
    public Long countPostValidation(Long userId) {
        Long countPosts;
        try {
            countPosts = postClient.getPostCountByUserId(userId).getBody();
        } catch (Exception e) {
            countPosts = 0L;
        }
        return countPosts;
    }
}
