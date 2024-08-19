package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.MediaEvent;
import org.thewhitemage13.interfaces.MediaDeleteEventHandlerInterface;
import org.thewhitemage13.service.MediaStatisticService;

@Component
@KafkaListener(topics = "media.deleted")
public class MediaDeleteEventHandler implements MediaDeleteEventHandlerInterface {
    private final MediaStatisticService mediaStatisticService;

    @Autowired
    public MediaDeleteEventHandler(MediaStatisticService mediaStatisticService) {
        this.mediaStatisticService = mediaStatisticService;
    }

    @Override
    @KafkaHandler
    public void mediaDelete(MediaEvent mediaEvent) {
        mediaStatisticService.deleteMediaStatistic(mediaEvent);
    }

}
