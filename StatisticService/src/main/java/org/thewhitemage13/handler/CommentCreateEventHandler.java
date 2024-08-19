package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.CommentEvent;
import org.thewhitemage13.interfaces.CommentCreateEventHandlerInterface;
import org.thewhitemage13.service.CommentStatisticService;

@Component
@KafkaListener(topics = "comment.created")
public class CommentCreateEventHandler implements CommentCreateEventHandlerInterface {
    private final CommentStatisticService commentStatisticService;

    @Autowired
    public CommentCreateEventHandler(CommentStatisticService commentStatisticService) {
        this.commentStatisticService = commentStatisticService;
    }

    @Override
    @KafkaHandler
    public void commentCreated(CommentEvent commentEvent) {
        commentStatisticService.createCommentStatistic();
    }
}
