package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.LikeEvent;
import org.thewhitemage13.interfaces.LikePostCreateEventHandlerInterface;
import org.thewhitemage13.service.LikeStatisticService;

@Component
@KafkaListener(topics = "post.like.created")
public class LikePostCreateEventHandler implements LikePostCreateEventHandlerInterface {
    private final LikeStatisticService likeStatisticService;

    @Autowired
    public LikePostCreateEventHandler(LikeStatisticService likeStatisticService) {
        this.likeStatisticService = likeStatisticService;
    }

    @Override
    @KafkaHandler
    public void likePostCreate(LikeEvent likeEvent) {
        likeStatisticService.createLikePostStatistic();
    }
}
