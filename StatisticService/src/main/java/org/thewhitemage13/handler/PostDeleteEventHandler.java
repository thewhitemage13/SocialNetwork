package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.PostEvent;
import org.thewhitemage13.interfaces.PostDeleteEventHandlerInterface;
import org.thewhitemage13.service.PostStatisticService;

@Component
@KafkaListener(topics = "post.deleted")
public class PostDeleteEventHandler implements PostDeleteEventHandlerInterface {
    private final PostStatisticService postStatisticService;

    @Autowired
    public PostDeleteEventHandler(PostStatisticService postStatisticService) {
        this.postStatisticService = postStatisticService;
    }

    @Override
    @KafkaHandler
    public void postDelete(PostEvent postEvent) {
        postStatisticService.deletePostStatistic(postEvent);
    }
}
