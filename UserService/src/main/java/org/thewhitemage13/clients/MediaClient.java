package org.thewhitemage13.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "media-service", path = "/media")
public interface MediaClient {
    @GetMapping("/media-verification")
    ResponseEntity<Boolean> mediaVerification(@RequestParam("url") String url);
}
