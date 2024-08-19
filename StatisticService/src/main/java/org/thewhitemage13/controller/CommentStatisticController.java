package org.thewhitemage13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.entity.CommentStatistic;
import org.thewhitemage13.service.CommentStatisticService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/comment-statistic")
public class CommentStatisticController {
    private final CommentStatisticService commentStatisticService;

    @Autowired
    public CommentStatisticController(CommentStatisticService commentStatisticService) {
        this.commentStatisticService = commentStatisticService;
    }

    @DeleteMapping("/delete-by-date")
    public ResponseEntity<String> deleteByDate(@RequestParam("date") LocalDate date) {
        try {
            commentStatisticService.deleteStatisticByDate(date);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted comment statistic");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/show-all")
    public ResponseEntity<List<CommentStatistic>> showAllStatistics() {
        try {
            return ResponseEntity.ok(commentStatisticService.showAllStatistics());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/show-by-date")
    public ResponseEntity<CommentStatistic> showStatisticByDate(@RequestParam("date") LocalDate date) {
        try {
            return ResponseEntity.ok(commentStatisticService.showStatisticsByDate(date));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
