package org.thewhitemage13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.entity.MediaStatistic;
import org.thewhitemage13.service.MediaStatisticService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/media-statistic")
public class MediaStatisticController {
    private final MediaStatisticService mediaStatisticService;

    @Autowired
    public MediaStatisticController(MediaStatisticService mediaStatisticService) {
        this.mediaStatisticService = mediaStatisticService;
    }

    @DeleteMapping("/delete-by-date")
    public ResponseEntity<String> deleteByDate(@RequestParam("date") LocalDate date) {
        try {
            mediaStatisticService.deleteStatisticByDate(date);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted media statistic");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/show-all")
    public ResponseEntity<List<MediaStatistic>> showAllStatistics() {
        try {
            return ResponseEntity.ok(mediaStatisticService.getAllMediaStatistics());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/show-by-date")
    public ResponseEntity<MediaStatistic> showStatisticByDate(@RequestParam("date") LocalDate date) {
        try {
            return ResponseEntity.ok(mediaStatisticService.getMediaStatisticByDate(date));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
