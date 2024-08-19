package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.exception.CommentNotFoundException;
import org.thewhitemage13.exception.MediaNotFoundException;
import org.thewhitemage13.interfaces.UserDeletedEventHandlerInterface;
import org.thewhitemage13.service.CommentService;

@Component
@KafkaListener(topics = "user.deleted")
public class UserDeletedEventHandler implements UserDeletedEventHandlerInterface {
    private final CommentService commentService;

    @Autowired
    public UserDeletedEventHandler(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @KafkaHandler
    public void userDeleted(UserEvent userEvent) throws MediaNotFoundException, CommentNotFoundException {
        commentService.deleteAllByUserId(userEvent.getUserId());
    }
}
