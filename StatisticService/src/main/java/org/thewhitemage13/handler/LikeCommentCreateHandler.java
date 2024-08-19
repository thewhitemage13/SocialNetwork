package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.LikeEvent;
import org.thewhitemage13.interfaces.LikeCommentCreateHandlerInterface;
import org.thewhitemage13.service.LikeStatisticService;

@Component
@KafkaListener(topics = "comment.like.created")
public class LikeCommentCreateHandler implements LikeCommentCreateHandlerInterface {
    private final LikeStatisticService likeStatisticService;

    @Autowired
    public LikeCommentCreateHandler(LikeStatisticService likeStatisticService) {
        this.likeStatisticService = likeStatisticService;
    }

    @Override
    @KafkaHandler
    public void likeCommentCreated(LikeEvent likeEvent) {
        likeStatisticService.createLikeCommentStatistic();
    }
}
