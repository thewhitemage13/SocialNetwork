package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.PostEvent;
import org.thewhitemage13.interfaces.PostCreateEventHandlerInterface;
import org.thewhitemage13.service.PostStatisticService;

@Component
@KafkaListener(topics = "post.created")
public class PostCreateEventHandler implements PostCreateEventHandlerInterface {
    private final PostStatisticService postStatisticService;

    @Autowired
    public PostCreateEventHandler(PostStatisticService postStatisticService) {
        this.postStatisticService = postStatisticService;
    }

    @Override
    @KafkaHandler
    public void postCreate(PostEvent postEvent) {
        postStatisticService.createPostStatistic(postEvent);
    }
}
