package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.SubscriptionEvent;
import org.thewhitemage13.interfaces.SubscriptionCreateEventHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "subscription.created")
public class SubscriptionCreateEventHandler implements SubscriptionCreateEventHandlerInterface {
    private final NotificationService notificationService;

    @Autowired
    public SubscriptionCreateEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @KafkaHandler
    public void subscriptionCreated(SubscriptionEvent subscriptionEvent) {
        notificationService.createNotification(new CreateNotificationDTO(subscriptionEvent.getFollowingId(), "SMS", "New follower = %s".formatted(subscriptionEvent.getFollowerId())));
    }
}