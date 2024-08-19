package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.interfaces.UserDeletedEventHandlerInterface;
import org.thewhitemage13.service.UserStatisticService;

@Component
@KafkaListener(topics = "user.deleted")
public class UserDeleteEventHandler implements UserDeletedEventHandlerInterface {
    private final UserStatisticService userStatisticService;

    @Autowired
    public UserDeleteEventHandler(UserStatisticService userStatisticService) {
        this.userStatisticService = userStatisticService;
    }

    @Override
    @KafkaHandler
    public void userDeleted(UserEvent userEvent) {
        userStatisticService.remoteUserStatistic(userEvent);
    }

}
