package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.CommentEvent;
import org.thewhitemage13.exception.LikeNotFoundException;
import org.thewhitemage13.service.LikeService;

@Component
@KafkaListener(topics = "comment.deleted")
public class CommentDeleteEventHandler {
    private final LikeService likeService;

    @Autowired
    public CommentDeleteEventHandler(LikeService likeService) {
        this.likeService = likeService;
    }

    @KafkaHandler
    public void postDeleted(CommentEvent commentEvent) throws LikeNotFoundException {
        likeService.deleteAllByCommentId(commentEvent.getCommentId());
    }
}
