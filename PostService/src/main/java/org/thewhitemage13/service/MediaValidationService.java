package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thewhitemage13.clients.MediaClient;
import org.thewhitemage13.dto.PostDTO;
import org.thewhitemage13.exception.MediaNotFoundException;
import org.thewhitemage13.interfaces.MediaValidationServiceInterface;

@Service
public class MediaValidationService implements MediaValidationServiceInterface {
    private final MediaClient mediaClient;

    @Autowired
    public MediaValidationService(MediaClient mediaClient) {
        this.mediaClient = mediaClient;
    }

    @Override
    public void isCreateMedia(PostDTO postDTO){
        Boolean status;

        ResponseEntity<Boolean> isCrateMedia = mediaClient.mediaVerification(postDTO.getMediaUrl());
        status = isCrateMedia.getBody();

        if (Boolean.FALSE.equals(status)) {
            throw new MediaNotFoundException("Media with url = %s not found".formatted(postDTO.getMediaUrl()));
        }
    }
}
