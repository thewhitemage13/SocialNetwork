package org.thewhitemage13.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "post-service", path = "/posts")
public interface PostClient {
    @GetMapping("/post-verification")
    ResponseEntity<Boolean> postVerification(@RequestParam("postId") Long postId);
}
