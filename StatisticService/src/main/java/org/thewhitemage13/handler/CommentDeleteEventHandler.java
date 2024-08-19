package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.CommentEvent;
import org.thewhitemage13.interfaces.CommentDeleteEventHandlerInterface;
import org.thewhitemage13.service.CommentStatisticService;

@Component
@KafkaListener(topics = "comment.deleted")
public class CommentDeleteEventHandler implements CommentDeleteEventHandlerInterface {
    private final CommentStatisticService commentStatisticService;

    @Autowired
    public CommentDeleteEventHandler(CommentStatisticService commentStatisticService) {
        this.commentStatisticService = commentStatisticService;
    }

    @Override
    @KafkaHandler
    public void commentDeleted(CommentEvent commentEvent) {
        commentStatisticService.deleteCommentStatistic();
    }
}
