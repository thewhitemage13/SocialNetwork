package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.MediaEvent;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.interfaces.MediaUploadEventHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "media.upload")
public class MediaUploadEventHandler implements MediaUploadEventHandlerInterface {
    private final NotificationService notificationService;

    @Autowired
    public MediaUploadEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @KafkaHandler
    public void mediaUpload(MediaEvent mediaEvent) {
        notificationService.createNotification(new CreateNotificationDTO(mediaEvent.getUserId(), "SMS", "Your file = %s  is uploaded successfully".formatted(mediaEvent.getUrl())));

    }
}
