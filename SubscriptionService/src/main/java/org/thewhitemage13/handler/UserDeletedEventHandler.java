package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.interfaces.UserDeletedEventHandlerInterface;
import org.thewhitemage13.service.SubscriptionService;

@Component
@KafkaListener(topics = "user.deleted")
public class UserDeletedEventHandler implements UserDeletedEventHandlerInterface {
    private final SubscriptionService subscriptionService;

    @Autowired
    public UserDeletedEventHandler(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    @KafkaHandler
    public void userDeleted(UserEvent userEvent) {
        subscriptionService.deleteFollowersById(userEvent.getUserId());
        subscriptionService.deleteFollowingById(userEvent.getUserId());
    }
}
