package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.LikeEvent;
import org.thewhitemage13.interfaces.LikePostDeleteEventHandlerInterface;
import org.thewhitemage13.service.LikeStatisticService;

@Component
@KafkaListener(topics = "post.like.deleted")
public class LikePostDeleteEventHandler implements LikePostDeleteEventHandlerInterface {
    private final LikeStatisticService likeStatisticService;

    @Autowired
    public LikePostDeleteEventHandler(LikeStatisticService likeStatisticService) {
        this.likeStatisticService = likeStatisticService;
    }

    @Override
    @KafkaHandler
    public void likePostDeleted(LikeEvent likeEvent) {
        likeStatisticService.deleteLikePostStatistic();
    }
}
