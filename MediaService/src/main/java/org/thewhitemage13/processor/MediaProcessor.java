package org.thewhitemage13.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.thewhitemage13.interfaces.MediaProcessorInterface;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Component
public class MediaProcessor implements MediaProcessorInterface {
    private final S3Client amazonS3;
    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    public MediaProcessor(S3Client amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public void uploadFileToS3(String key, MultipartFile file) throws IOException {
        amazonS3.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    }

    @Override
    public void deleteFileFromS3(String key) {
        amazonS3.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build());
    }

    @Override
    public String generateS3Url(String key) {
        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }

    @Override
    public String generateFileName(MultipartFile file) {
        return UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
    }
}
