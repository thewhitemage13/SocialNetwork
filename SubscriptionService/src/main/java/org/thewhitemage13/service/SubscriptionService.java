package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.clients.UserClient;
import org.thewhitemage13.dao.SubscriptionDAO;
import org.thewhitemage13.dto.UserSubscriptionDTO;
import org.thewhitemage13.entity.Subscription;
import org.thewhitemage13.exceotion.SubscriptionNotFoundException;
import org.thewhitemage13.SubscriptionEvent;
import org.thewhitemage13.interfaces.SubscriptionInterface;
import org.thewhitemage13.processor.SubscriptionProcessor;
import org.thewhitemage13.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubscriptionService implements SubscriptionInterface {
    private final SubscriptionProcessor subscriptionProcessor;
    private final SubscriptionRepository subscriptionRepository;
    private final ValidationService validationService;
    private final KafkaTemplate<Long, Object> kafkaTemplate;
    private final UserClient userClient;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, ValidationService validationService, KafkaTemplate<Long, Object> kafkaTemplate, UserClient userClient, SubscriptionProcessor subscriptionProcessor) {
        this.subscriptionRepository = subscriptionRepository;
        this.validationService = validationService;
        this.kafkaTemplate = kafkaTemplate;
        this.userClient = userClient;
        this.subscriptionProcessor = subscriptionProcessor;
    }

    @Override
    public List<SubscriptionDAO> getAllFollowingByFollowerId(Long followerId) {
        List<Subscription> get = subscriptionRepository.findAllByFollowerId(followerId);
        return subscriptionProcessor.getSubscriptionDAOS(get);
    }

    @Override
    public List<SubscriptionDAO> getAllFollowingByFollowingId(Long followingId) {
        List<Subscription> get = subscriptionRepository.findAllByFollowingId(followingId);
        return subscriptionProcessor.getSubscriptionDAOS(get);
    }

    @Override
    public boolean followingVerification(Long followerId, Long followingId) {
        return subscriptionRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }

    @Override
    public Long countFollowersByFollowingId(Long followingId) {
        return subscriptionRepository.countByFollowingId(followingId);
    }

    @Override
    public Long countFollowingByFollower(Long followerId) {
        return subscriptionRepository.countByFollowerId(followerId);
    }

    @Override
    public void deleteFollowersById(Long followerId) {
        subscriptionRepository.deleteAllByFollowerId(followerId);
    }

    @Override
    public void deleteFollowingById(Long followingId) {
        subscriptionRepository.deleteAllByFollowingId(followingId);
    }

    @Override
    public List<UserSubscriptionDTO> getFollowers(Long userId) {
        List<Subscription> subscriptions = subscriptionRepository.findAllByFollowingId(userId);
        List<Long> followerIds = subscriptions.stream()
                .map(Subscription::getFollowerId)
                .collect(Collectors.toList());
        System.out.println(followerIds);
        return userClient.getUsersByIds(followerIds);
    }

    @Override
    public List<UserSubscriptionDTO> getFollowing(Long userId) {
        List<Subscription> subscriptions = subscriptionRepository.findAllByFollowerId(userId);
        List<Long> followingIds = subscriptions.stream()
                .map(Subscription::getFollowingId)
                .collect(Collectors.toList());
        System.out.println(subscriptions);
        return userClient.getUsersByIds(followingIds);
    }

    @Override
    public void createSubscription(Long followerId, Long followingId) {
        validationService.validateSubscriptionVerification(followerId, followingId);
        validationService.validateUser(followerId);
        validationService.validateUser(followingId);
        Subscription subscription = new Subscription();
        subscription.setCreatedAt(LocalDateTime.now());
        subscription.setFollowingId(followingId);
        subscription.setFollowerId(followerId);
        subscriptionRepository.save(subscription);
        SubscriptionEvent subscriptionEvent = subscriptionProcessor.getSubscriptionEvent(subscription);
        kafkaTemplate.send("subscription.created", subscription.getSubscriptionId(), subscriptionEvent);
    }

    @Override
    public void deleteSubscription(Long followerId, Long followingId) {
        Subscription delete = subscriptionRepository.findByFollowerIdAndFollowingId(followerId, followingId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription with followerId = %s and followingId = %s not found".formatted(followerId, followingId)));
        subscriptionRepository.delete(delete);
        SubscriptionEvent subscriptionEvent = subscriptionProcessor.getSubscriptionEvent(delete);
        kafkaTemplate.send("subscription.deleted", delete.getSubscriptionId(), subscriptionEvent);
    }
}
