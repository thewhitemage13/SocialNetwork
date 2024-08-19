package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.UserClient;
import org.thewhitemage13.exception.UserNotFoundException;
import org.thewhitemage13.interfaces.ValidationServiceInterface;

@Service
public class ValidationService implements ValidationServiceInterface {
    private final UserClient userClient;

    @Autowired
    public ValidationService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public void validateUser(Long userId) {
        ResponseEntity<Boolean> isCreated = userClient.userVerification(userId);
        Boolean status = isCreated.getBody();

        if(Boolean.FALSE.equals(status)) {
            throw new UserNotFoundException("User with id = %s not found".formatted(userId));
        }
    }
}
