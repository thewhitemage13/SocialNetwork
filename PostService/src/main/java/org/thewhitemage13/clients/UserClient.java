package org.thewhitemage13.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", path = "/users")
public interface UserClient {
    @GetMapping("/user-verification")
    ResponseEntity<Boolean> userVerification(@RequestParam("userId") Long userId);

    @GetMapping("/get-username-by-id/{userId}")
    ResponseEntity<String> getUserNameById(@PathVariable("userId") Long userId);
}
