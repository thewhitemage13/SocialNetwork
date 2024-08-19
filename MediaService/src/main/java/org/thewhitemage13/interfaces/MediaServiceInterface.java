package org.thewhitemage13.interfaces;

import org.springframework.web.multipart.MultipartFile;
import org.thewhitemage13.entity.Media;
import org.thewhitemage13.exception.MediaNotFoundException;

import java.io.IOException;

public interface MediaServiceInterface {
    boolean mediaVerification(String url);
    String uploadMedia(Long userId, MultipartFile file) throws IOException;
    void deleteMedia(Long id) throws MediaNotFoundException;
    Media getMedia(Long id) throws MediaNotFoundException;
}
