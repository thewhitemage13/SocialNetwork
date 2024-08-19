package org.thewhitemage13.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "comment-service", path = "/comments")
public interface CommentClient {
    @GetMapping("/get-count-by-postId")
    ResponseEntity<Long> getCommentCountByPostId(@RequestParam("postId") Long postId);
}
