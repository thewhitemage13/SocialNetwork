package org.thewhitemage13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thewhitemage13.entity.Media;
import org.thewhitemage13.exception.MediaNotFoundException;
import org.thewhitemage13.service.MediaService;

import java.io.IOException;

@RestController
@RequestMapping("/media")
public class MediaController {
    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/media-verification")
    public ResponseEntity<Boolean> mediaVerification(@RequestParam("url") String url) {
        try {
            return ResponseEntity.ok(mediaService.mediaVerification(url));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<String> upload(@PathVariable("userId") Long userId, @RequestPart("file") MultipartFile file) {
        try {
            String fileUrl = mediaService.uploadMedia(userId, file);
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("mediaId") Long mediaId) {
        try {
            mediaService.deleteMedia(mediaId);
            return ResponseEntity.ok("File deleted successfully.");
        } catch (MediaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Media with id = %s not found.".formatted(mediaId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }

    }

    @GetMapping("/get-information")
    public ResponseEntity<String> getInformation(@RequestParam("userId") Long mediaId) {
        try {
            Media media = mediaService.getMedia(mediaId);
            return ResponseEntity.ok(media.toString());
        }catch (MediaNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Media with id = %s not found.".formatted(mediaId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }

}
