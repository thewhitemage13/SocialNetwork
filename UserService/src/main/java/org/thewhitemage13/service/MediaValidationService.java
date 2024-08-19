package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.MediaClient;
import org.thewhitemage13.clients.PostClient;
import org.thewhitemage13.dto.UserDTO;
import org.thewhitemage13.exception.MediaNotFoundException;
import org.thewhitemage13.interfaces.MediaValidationServiceInterface;

import java.util.Collections;
import java.util.List;

@Service
public class MediaValidationService implements MediaValidationServiceInterface {
    private final MediaClient mediaClient;
    private final PostClient postClient;

    @Autowired
    public MediaValidationService(MediaClient mediaClient, PostClient postClient) {
        this.mediaClient = mediaClient;
        this.postClient = postClient;
    }

    @Override
    public List<String> validateMedia(Long userId) {
        List<String> mediaPostsUrl;
        try {
            mediaPostsUrl = postClient.getMediaUrlByUserId(userId).getBody();
        } catch (Exception e) {
            mediaPostsUrl = Collections.emptyList();
        }
        return mediaPostsUrl;
    }

    @Override
    public void validatePicture(UserDTO userDTO) {
        if (userDTO.getProfilePictureUrl() != null) {
            ResponseEntity<Boolean> isCrate = mediaClient.mediaVerification(userDTO.getProfilePictureUrl());
            Boolean result = isCrate.getBody();
            if (Boolean.FALSE.equals(result)) {
                throw new MediaNotFoundException("The profile picture with url = %s not found".formatted(userDTO.getProfilePictureUrl()));
            }
        }
    }
}
