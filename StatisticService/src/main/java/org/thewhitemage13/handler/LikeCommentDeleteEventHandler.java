package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.LikeEvent;
import org.thewhitemage13.interfaces.LikeCommentDeleteEventHandlerInterface;
import org.thewhitemage13.service.LikeStatisticService;

@Component
@KafkaListener(topics = "comment.like.deleted")
public class LikeCommentDeleteEventHandler implements LikeCommentDeleteEventHandlerInterface {
    private final LikeStatisticService likeStatisticService;

    @Autowired
    public LikeCommentDeleteEventHandler(LikeStatisticService likeStatisticService) {
        this.likeStatisticService = likeStatisticService;
    }

    @Override
    @KafkaHandler
    public void likeCommentDeleted(LikeEvent likeEvent) {
        likeStatisticService.deleteLikeCommentStatistic();
    }
}