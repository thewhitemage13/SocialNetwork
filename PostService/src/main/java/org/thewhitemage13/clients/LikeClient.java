package org.thewhitemage13.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "like-service", path = "/likes")
public interface LikeClient {
    @GetMapping("/get-post-like-count")
    ResponseEntity<Long> getPostLikeCount(@RequestParam("postId") Long postId);
}
