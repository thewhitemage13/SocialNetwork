package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.MediaEvent;
import org.thewhitemage13.interfaces.MediaUploadEventHandlerInterface;
import org.thewhitemage13.service.MediaStatisticService;

@Component
@KafkaListener(topics = "media.upload")
public class MediaUploadEventHandler implements MediaUploadEventHandlerInterface {
    private final MediaStatisticService mediaStatisticService;

    @Autowired
    public MediaUploadEventHandler(MediaStatisticService mediaStatisticService) {
        this.mediaStatisticService = mediaStatisticService;
    }

    @Override
    @KafkaHandler
    public void mediaUpload(MediaEvent mediaEvent) {
        mediaStatisticService.uploadMediaStatistic(mediaEvent);
    }
}
