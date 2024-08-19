package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thewhitemage13.MediaEvent;
import org.thewhitemage13.entity.Media;
import org.thewhitemage13.exception.MediaNotFoundException;
import org.thewhitemage13.interfaces.MediaServiceInterface;
import org.thewhitemage13.processor.MediaProcessor;
import org.thewhitemage13.repository.MediaRepository;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MediaService implements MediaServiceInterface {
    private final MediaRepository mediaRepository;
    private final ValidationService validationService;
    private final KafkaTemplate<Long, Object> kafkaTemplate;
    private final MediaProcessor mediaProcessor;

    @Autowired
    public MediaService(S3Client s3Client, MediaRepository mediaRepository, ValidationService validationService, KafkaTemplate<Long, Object> kafkaTemplate, MediaProcessor mediaProcessor) {
        this.mediaRepository = mediaRepository;
        this.validationService = validationService;
        this.kafkaTemplate = kafkaTemplate;
        this.mediaProcessor = mediaProcessor;
    }

    @Override
    public boolean mediaVerification(String url) {
        return mediaRepository.existsByUrl(url);
    }

    @Override
    public String uploadMedia(Long userId, MultipartFile file) throws IOException {
        validationService.validateUser(userId);

        Media media = new Media();
        String fileName = mediaProcessor.generateFileName(file);
        String key = "media/" + fileName;

        mediaProcessor.uploadFileToS3(key, file);

        String url = mediaProcessor.generateS3Url(key);
        media.setUrl(url);

        media.setUserId(userId);
        media.setFileName(fileName);
        media.setFileSize((double) file.getSize());
        media.setFileType(file.getContentType());
        media.setUploadDate(LocalDateTime.now());

        mediaRepository.save(media);

        MediaEvent mediaEvent = new MediaEvent
                (
                        media.getMediaId(),
                        media.getUserId(),
                        media.getUrl(),
                        media.getFileName(),
                        media.getFileSize(),
                        media.getFileType(),
                        media.getUploadDate()
                );

        kafkaTemplate.send("media.upload", media.getMediaId(), mediaEvent);

        return url;
    }

    public void deleteAllByUserId(Long userId) throws MediaNotFoundException {
        List<Media> mediaList = mediaRepository.findAllByUserId(userId);

        for (Media media : mediaList) {
            deleteMedia(media.getMediaId());
        }
    }

    @Override
    public void deleteMedia(Long id) throws MediaNotFoundException {
        Media media = mediaRepository.findById(id).orElseThrow(() -> new MediaNotFoundException("Media with id = %s not found".formatted(id)));

        String key = "media/" + media.getFileName();

        mediaProcessor.deleteFileFromS3(key);

        mediaRepository.deleteById(id);

        MediaEvent mediaEvent = new MediaEvent
                (
                        media.getMediaId(),
                        media.getUserId(),
                        media.getUrl(),
                        media.getFileName(),
                        media.getFileSize(),
                        media.getFileType(),
                        media.getUploadDate()
                );

        kafkaTemplate.send("media.deleted", media.getMediaId(), mediaEvent);
    }

    @Override
    public Media getMedia(Long id) throws MediaNotFoundException {
        return mediaRepository.findById(id).orElseThrow(() -> new MediaNotFoundException("Media with id = %s not found".formatted(id)));
    }
}
