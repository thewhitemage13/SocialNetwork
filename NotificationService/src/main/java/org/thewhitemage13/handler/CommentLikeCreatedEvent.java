package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.LikeEvent;
import org.thewhitemage13.clients.CommentClient;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.interfaces.LikeCommentCreateHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "comment.like.created")
public class CommentLikeCreatedEvent implements LikeCommentCreateHandlerInterface {
    private final NotificationService notificationService;
    private final CommentClient commentClient;

    @Autowired
    public CommentLikeCreatedEvent(NotificationService notificationService, CommentClient commentClient) {
        this.notificationService = notificationService;
        this.commentClient = commentClient;
    }

    @Override
    @KafkaHandler
    public void likeCommentCreated(LikeEvent likeEvent) {
        Long user = commentClient.getCommentUserIdByCommentId(likeEvent.getCommentId()).getBody();

        notificationService.createNotification
                (new CreateNotificationDTO(user ,"SMS", "User with id = %s like your comment with id = %s"
                        .formatted(likeEvent.getUserId(), likeEvent.getCommentId())));
    }
}
