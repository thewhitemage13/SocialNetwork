package org.thewhitemage13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.dao.SubscriptionDAO;
import org.thewhitemage13.dto.UserSubscriptionDTO;
import org.thewhitemage13.exceotion.SubscriptionNotFoundException;
import org.thewhitemage13.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<UserSubscriptionDTO>> getFollowers(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(subscriptionService.getFollowers(userId));
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<UserSubscriptionDTO>> getFollowing(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(subscriptionService.getFollowing(userId));
    }

    @GetMapping("/count-followers")
    public ResponseEntity<Long> countFollowersByFollowingId(@RequestParam("followingId") Long followingId) {
        return ResponseEntity.ok(subscriptionService.countFollowingByFollower(followingId));
    }

    @GetMapping("/count-following")
    public ResponseEntity<Long> countFollowingByFollower(@RequestParam("followerId") Long followerId) {
        return ResponseEntity.ok(subscriptionService.countFollowersByFollowingId(followerId));
    }

    @GetMapping("/following-verification")
    public ResponseEntity<Boolean> verificationFollowing(@RequestParam("followerId") Long followerId, @RequestParam("followingId") Long followingId) {
        try {
            boolean isFollowing = subscriptionService.followingVerification(followerId, followingId);
            return ResponseEntity.ok(isFollowing);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/get-following-by-followingId/{followingId}")
    public ResponseEntity<List<SubscriptionDAO>> getAllFollowingByFollowingId(@PathVariable("followingId") Long followingId) {
        try {
            return ResponseEntity.ok(subscriptionService.getAllFollowingByFollowingId(followingId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get-following-by-follower/{followerId}")
    public ResponseEntity<List<SubscriptionDAO>> getAllFollowingByFollowerId(@PathVariable("followerId") Long followerId) {
        try {
            return ResponseEntity.ok(subscriptionService.getAllFollowingByFollowerId(followerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSubscription(@RequestParam("followerId") Long followerId,@RequestParam("followingId") Long followingId) {
        try {
            subscriptionService.createSubscription(followerId, followingId);
            return ResponseEntity.ok("Subscription created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSubscription(@RequestParam("followerId") Long followerId, @RequestParam("followerId") Long followingId) {
        try {
            subscriptionService.deleteSubscription(followerId, followingId);
            return ResponseEntity.ok("Subscription deleted");
        } catch (SubscriptionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription with followerId = %s and followingId = %s not found".formatted(followerId, followingId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
