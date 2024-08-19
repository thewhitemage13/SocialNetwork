package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.UserClient;
import org.thewhitemage13.dto.PostDTO;
import org.thewhitemage13.exception.UserNotFoundException;
import org.thewhitemage13.interfaces.UserValidationServiceInterface;

@Service
public class UserValidationService implements UserValidationServiceInterface {
    private final UserClient userClient;

    @Autowired
    public UserValidationService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public void validateUser(PostDTO postDTO){
        Boolean status;
        ResponseEntity<Boolean> isCreateUser = userClient.userVerification(postDTO.getUserId());
        status = isCreateUser.getBody();

        if (Boolean.FALSE.equals(status)) {
            throw new UserNotFoundException("User with id = %s not found".formatted(postDTO.getUserId()));
        }
    }
}
