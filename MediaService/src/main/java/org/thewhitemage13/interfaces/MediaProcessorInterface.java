package org.thewhitemage13.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaProcessorInterface {
    void uploadFileToS3(String key, MultipartFile file) throws IOException;
    void deleteFileFromS3(String key);
    String generateS3Url(String key);
    String generateFileName(MultipartFile file);
}
