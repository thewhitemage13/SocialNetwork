package org.thewhitemage13.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.dto.CommentCreateDto;
import org.thewhitemage13.exception.CommentNotFoundException;
import org.thewhitemage13.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/get-count-by-postId")
    public ResponseEntity<Long> getCommentCountByPostId(@RequestParam("postId") Long postId) {
        try {
            return ResponseEntity.ok(commentService.getCountOfCommentsByPostId(postId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get-userId-by-commentId")
    public ResponseEntity<Long> getCommentUserIdByCommentId(@RequestParam("commentId") Long commentId) {
        try {
            return ResponseEntity.ok(commentService.getUserIdByCommentId(commentId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/comment-verification")
    public ResponseEntity<Boolean> commentVerification(@RequestParam("commentId") Long commentId) {
        try {
            return ResponseEntity.ok(commentService.commentVerification(commentId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestBody CommentCreateDto commentCreateDto) {
        try {
            commentService.addComment(commentCreateDto);
            return ResponseEntity.ok("Comment added");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable("commentId") Long commentId ,@RequestBody CommentCreateDto commentCreateDto) {
        try {
            commentService.updateComment(commentId, commentCreateDto);
            return ResponseEntity.ok("Comment updated");
        }catch (CommentNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment with id = %s not found".formatted(commentId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestParam("commentId") Long commentId) {
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.ok("Comment deleted");
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment with id = %s not found".formatted(commentId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }

    }

    @GetMapping("/get-all/{postId}")
    public ResponseEntity<String> getAllComments(@PathVariable("postId") Long postId) {
        try {
            return ResponseEntity.ok(commentService.getAllByPostId(postId).toString());
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comments with post id = %s not found".formatted(postId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}
