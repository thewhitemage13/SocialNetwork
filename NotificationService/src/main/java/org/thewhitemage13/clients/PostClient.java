package org.thewhitemage13.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "post-service", path = "/posts")
public interface PostClient {
    @GetMapping("/get-userId-by-postId")
    ResponseEntity<Long> getUserIdByPostId(@RequestParam("postId") Long postId);
}
