package org.thewhitemage13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.entity.UserStatistic;
import org.thewhitemage13.service.UserStatisticService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user-statistic")
public class UserStatisticController {
    private final UserStatisticService userStatisticService;

    @Autowired
    public UserStatisticController(UserStatisticService userStatisticService) {
        this.userStatisticService = userStatisticService;
    }

    @DeleteMapping("/delete-by-date")
    public ResponseEntity<String> deleteByDate(@RequestParam("date") LocalDate date) {
        try {
            userStatisticService.deleteStatisticByDate(date);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted user statistic");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/show-all")
    public ResponseEntity<List<UserStatistic>> showAllStatistics() {
        try {
            return ResponseEntity.ok(userStatisticService.getUserStatistics());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/show-by-date")
    public ResponseEntity<UserStatistic> showStatisticByDate(@RequestParam("date") LocalDate date) {
        try {
            return ResponseEntity.ok(userStatisticService.getUserStatisticByDate(date));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
