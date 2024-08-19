package org.thewhitemage13.processor;

import org.springframework.stereotype.Component;
import org.thewhitemage13.SubscriptionEvent;
import org.thewhitemage13.dao.SubscriptionDAO;
import org.thewhitemage13.entity.Subscription;
import org.thewhitemage13.interfaces.SubscriptionProcessorInterface;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubscriptionProcessor implements SubscriptionProcessorInterface {

    @Override
    public SubscriptionEvent getSubscriptionEvent(Subscription subscription) {
        return new SubscriptionEvent
                (
                        subscription.getSubscriptionId(),
                        subscription.getFollowerId(),
                        subscription.getFollowingId(),
                        subscription.getCreatedAt()
                );
    }

    @Override
    public List<SubscriptionDAO> getSubscriptionDAOS(List<Subscription> get) {
        List<SubscriptionDAO> subscriptions = new ArrayList<>();
        for (Subscription subscription : get) {
            SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
            subscriptionDAO.setFollowingId(subscription.getFollowingId());
            subscriptionDAO.setFollowerId(subscription.getFollowerId());
            subscriptions.add(subscriptionDAO);
        }
        return subscriptions;
    }
}
