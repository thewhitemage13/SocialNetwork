package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.exception.LikeNotFoundException;
import org.thewhitemage13.exception.MediaNotFoundException;
import org.thewhitemage13.service.LikeService;

@Component
@KafkaListener(topics = "user.deleted")
public class UserDeletedEventHandler {
    private final LikeService likeService;

    @Autowired
    public UserDeletedEventHandler(LikeService likeService) {
        this.likeService = likeService;
    }

    @KafkaHandler
    public void userDeleted(UserEvent userEvent) throws MediaNotFoundException, LikeNotFoundException {
        likeService.deleteAllByUserId(userEvent.getUserId());
    }
}
