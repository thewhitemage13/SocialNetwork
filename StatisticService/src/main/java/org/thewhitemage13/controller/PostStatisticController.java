package org.thewhitemage13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.entity.PostStatistic;
import org.thewhitemage13.service.PostStatisticService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/post-statistic")
public class PostStatisticController {
    private final PostStatisticService postStatisticService;

    @Autowired
    public PostStatisticController(PostStatisticService postStatisticService) {
        this.postStatisticService = postStatisticService;
    }

    @DeleteMapping("/delete-by-date")
    public ResponseEntity<String> deleteByDate(@RequestParam("date") LocalDate date) {
        try {
            postStatisticService.deleteStatisticByDate(date);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted post statistic");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/show-all")
    public ResponseEntity<List<PostStatistic>> showAllStatistics() {
        try {
            return ResponseEntity.ok(postStatisticService.getPostStatistics());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/show-by-date")
    public ResponseEntity<PostStatistic> showStatisticByDate(@RequestParam("date") LocalDate date) {
        try {
            return ResponseEntity.ok(postStatisticService.getStatisticByDate(date));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
