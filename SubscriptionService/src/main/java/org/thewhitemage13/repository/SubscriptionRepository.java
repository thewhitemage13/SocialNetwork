package org.thewhitemage13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thewhitemage13.entity.Subscription;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAllByFollowerId(Long followerId);
    List<Subscription> findAllByFollowingId(Long followingId);
    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
    Optional<Subscription> findByFollowerIdAndFollowingId(Long followerId, Long followingId);
    Long countByFollowerId(Long followerId);
    Long countByFollowingId(Long followingId);
    void deleteAllByFollowerId(Long followerId);
    void deleteAllByFollowingId(Long followingId);

}
