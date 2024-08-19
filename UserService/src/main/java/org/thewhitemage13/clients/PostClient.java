package org.thewhitemage13.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "post-service", path = "/posts")
public interface PostClient {
    @GetMapping("/get-count-by-user-id")
    ResponseEntity<Long> getPostCountByUserId(@RequestParam("userId") Long userId);

    @GetMapping("/get-mediaUrl-by-userId")
    ResponseEntity<List<String>> getMediaUrlByUserId(@RequestParam("userId") Long userId);
}
