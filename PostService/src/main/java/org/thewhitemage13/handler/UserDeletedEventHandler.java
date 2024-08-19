package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.exception.PostNotFoundException;
import org.thewhitemage13.service.PostService;

@Component
@KafkaListener(topics = "user.deleted")
public class UserDeletedEventHandler {
    private final PostService postService;

    @Autowired
    public UserDeletedEventHandler(PostService postService) {
        this.postService = postService;
    }

    @KafkaHandler
    public void userDeleted(UserEvent userEvent) throws PostNotFoundException {
        postService.deleteAllByUserId(userEvent.getUserId());
    }
}
