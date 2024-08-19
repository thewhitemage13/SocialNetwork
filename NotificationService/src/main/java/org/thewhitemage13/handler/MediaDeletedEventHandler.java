package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.MediaEvent;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.interfaces.MediaDeleteEventHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "media.deleted")
public class MediaDeletedEventHandler implements MediaDeleteEventHandlerInterface {
    private final NotificationService notificationService;

    @Autowired
    public MediaDeletedEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @KafkaHandler
    public void mediaDelete(MediaEvent mediaEvent) {
        notificationService.createNotification(new CreateNotificationDTO(mediaEvent.getUserId(), "SMS", "Your file = %s deleted".formatted(mediaEvent.getUrl())));
    }

}
