package org.thewhitemage13.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.PostClient;
import org.thewhitemage13.clients.UserClient;
import org.thewhitemage13.dto.CommentCreateDto;
import org.thewhitemage13.exception.PostNotFoundException;
import org.thewhitemage13.exception.UserNotFoundException;
import org.thewhitemage13.interfaces.ValidationServiceInterface;

@Service
public class ValidationService implements ValidationServiceInterface {
    private final PostClient postClient;
    private final UserClient userClient;

    public ValidationService(PostClient postClient, UserClient userClient) {
        this.postClient = postClient;
        this.userClient = userClient;
    }

    @Override
    public void validateUser(CommentCreateDto commentCreateDto) {
        Boolean status;

        ResponseEntity<Boolean> userIsCreate = userClient.userVerification(commentCreateDto.getUserId());
        status = userIsCreate.getBody();

        if (Boolean.FALSE.equals(status)) {
            throw new UserNotFoundException("User with id = %s not found".formatted(commentCreateDto.getUserId()));
        }
    }

    @Override
    public void validatePost(CommentCreateDto commentCreateDto) {
        Boolean status;

        ResponseEntity<Boolean> postIsCreate = postClient.postVerification(commentCreateDto.getPostId());
        status = postIsCreate.getBody();

        if (Boolean.FALSE.equals(status)) {
            throw new PostNotFoundException("Post with id = %s not found".formatted(commentCreateDto.getPostId()));
        }
    }

}
