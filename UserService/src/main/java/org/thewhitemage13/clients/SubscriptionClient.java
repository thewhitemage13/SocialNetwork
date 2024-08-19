package org.thewhitemage13.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "subscription-service", path = "/subscriptions")
public interface SubscriptionClient {
    @GetMapping("/count-followers")
    ResponseEntity<Long> countFollowersByFollowingId(@RequestParam("followingId") Long followingId);

    @GetMapping("/count-following")
    ResponseEntity<Long> countFollowingByFollower(@RequestParam("followerId") Long followerId);
}
