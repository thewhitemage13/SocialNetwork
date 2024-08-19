package org.thewhitemage13.interfaces;

import org.thewhitemage13.SubscriptionEvent;
import org.thewhitemage13.dao.SubscriptionDAO;
import org.thewhitemage13.entity.Subscription;

import java.util.List;

public interface SubscriptionProcessorInterface {
    SubscriptionEvent getSubscriptionEvent(Subscription subscription);
    List<SubscriptionDAO> getSubscriptionDAOS(List<Subscription> get);
}
