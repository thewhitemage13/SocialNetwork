package org.thewhitemage13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.entity.LikeStatistic;
import org.thewhitemage13.service.LikeStatisticService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/like-statistic")
public class LikeStatisticController {
    private final LikeStatisticService likeStatisticService;

    @Autowired
    public LikeStatisticController(LikeStatisticService likeStatisticService) {
        this.likeStatisticService = likeStatisticService;
    }

    @DeleteMapping("/delete-by-date")
    public ResponseEntity<String> deleteByDate(@RequestParam("date") LocalDate date) {
        try {
            likeStatisticService.deleteStatisticByDate(date);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted like statistic");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/show-all")
    public ResponseEntity<List<LikeStatistic>> showAllStatistics() {
        try {
            return ResponseEntity.ok(likeStatisticService.getAllLikeStatistics());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/show-by-date")
    public ResponseEntity<LikeStatistic> showStatisticByDate(@RequestParam("date") LocalDate date) {
        try {
            return ResponseEntity.ok(likeStatisticService.getLikeStatisticByDate(date));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
