package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.PostEvent;
import org.thewhitemage13.exception.LikeNotFoundException;
import org.thewhitemage13.service.LikeService;

@Component
@KafkaListener(topics = "post.deleted")
public class PostDeleteEventHandler {
    private final LikeService likeService;

    @Autowired
    public PostDeleteEventHandler(LikeService likeService) {
        this.likeService = likeService;
    }

    @KafkaHandler
    public void postDeleted(PostEvent postEvent) throws LikeNotFoundException {
        likeService.deleteAllByPostId(postEvent.getPostId());
    }
}
