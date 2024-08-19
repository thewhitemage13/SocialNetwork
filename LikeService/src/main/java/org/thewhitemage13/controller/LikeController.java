package org.thewhitemage13.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.dto.CreateLikeComment;
import org.thewhitemage13.dto.CreateLikePost;
import org.thewhitemage13.exception.LikeNotFoundException;
import org.thewhitemage13.service.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/get-post-like-count")
    public ResponseEntity<Long> getPostLikeCount(@RequestParam("postId") Long postId) {
        try {
            return ResponseEntity.ok(likeService.getPostLikeCount(postId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/post-like")
    public ResponseEntity<String> postLike(@RequestBody CreateLikePost createLikePost) {
        try {
            likeService.postLike(createLikePost);
            return ResponseEntity.ok("Post like successful");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/comment-like")
    public ResponseEntity<String> commentLike(@RequestBody CreateLikeComment createLikeComment) {
        try {
            likeService.commentLike(createLikeComment);
            return ResponseEntity.ok("Post like successful");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLike(@RequestParam("likeId") Long likeId) {
        try {
            likeService.deleteLike(likeId);
            return ResponseEntity.ok("Delete successful");
        }catch (LikeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Like with id = %s not found".formatted(likeId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/get-all-post")
    public Long getAllPostLikes(@RequestParam("postId") Long postId) {
        return likeService.showPostLikeSum(postId);
    }

    @GetMapping("/get-all-comment")
    public Long getAllCommentLikes(@RequestParam("commentId") Long commentId) {
        return likeService.showCommentLikeSum(commentId);
    }

}
