package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.interfaces.UserCreatedEventHandlerInterface;
import org.thewhitemage13.service.UserStatisticService;

@Component
@KafkaListener(topics = "user.created")
public class UserCreateEventHandler implements UserCreatedEventHandlerInterface {
    private final UserStatisticService userStatisticService;

    @Autowired
    public UserCreateEventHandler(UserStatisticService userStatisticService) {
        this.userStatisticService = userStatisticService;
    }

    @Override
    @KafkaHandler
    public void userCreated(UserEvent userEvent) {
        userStatisticService.createUserStatistic(userEvent);
    }
}
