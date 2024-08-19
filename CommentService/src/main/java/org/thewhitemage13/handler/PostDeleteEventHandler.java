package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.PostEvent;
import org.thewhitemage13.exception.CommentNotFoundException;
import org.thewhitemage13.service.CommentService;

@Component
@KafkaListener(topics = "post.deleted")
public class PostDeleteEventHandler {
    private final CommentService commentService;

    @Autowired
    public PostDeleteEventHandler(CommentService commentService) {
        this.commentService = commentService;
    }

    @KafkaHandler
    public void postDeleted(PostEvent postEvent) throws CommentNotFoundException {
        commentService.deleteAllByPostId(postEvent.getPostId());
    }
}