package org.thewhitemage13.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thewhitemage13.dto.UserSubscriptionDTO;

import java.util.List;

@FeignClient(name = "user-service", path = "/users")
public interface UserClient {
    @GetMapping("/user-verification")
    ResponseEntity<Boolean> userVerification(@RequestParam("userId") Long userId);

    @GetMapping("/get-users-by-Ids")
    List<UserSubscriptionDTO> getUsersByIds(@RequestParam("ids") List<Long> ids);
}
