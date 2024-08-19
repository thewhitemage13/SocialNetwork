package org.thewhitemage13.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.dto.OpenPostDTO;
import org.thewhitemage13.dto.PostDTO;
import org.thewhitemage13.dto.UpdatePostDTO;
import org.thewhitemage13.exception.PostNotFoundException;
import org.thewhitemage13.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/get-userId-by-postId")
    public ResponseEntity<Long> getUserIdByPostId(@RequestParam("postId") Long postId) {
        try {
            return ResponseEntity.ok(postService.getUserIdByPostId(postId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/open-all-by-userId")
    public ResponseEntity<List<OpenPostDTO>> findAllByUserId(@RequestParam("userId") Long userId) {
        try {
            return ResponseEntity.ok(postService.openAllPostsByUserId(userId));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-mediaUrl-by-userId")
    public ResponseEntity<List<String>> getMediaUrlByUserId(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(postService.getUrlsByUserId(userId));
    }

    @GetMapping("/get-count-by-user-id")
    public ResponseEntity<Long> getPostCountByUserId(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(postService.getCountPostByUserId(userId));
    }

    @GetMapping("/open-post")
    public ResponseEntity<OpenPostDTO> openPost(@RequestParam("postId") Long postId) throws PostNotFoundException {
        return ResponseEntity.ok(postService.openPost(postId));
    }

    @GetMapping("/post-verification")
    public ResponseEntity<Boolean> postVerification(@RequestParam("postId") Long postId) {
        try {
            return ResponseEntity.ok(postService.postVerification(postId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewPost(@RequestBody PostDTO postDTO) {
        try {
            postService.createPost(postDTO);
            return ResponseEntity.ok("Post created");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable("postId") Long postId , @RequestBody UpdatePostDTO updatePostDTO) {
        try {
            postService.updatePost(postId, updatePostDTO);
            return ResponseEntity.ok("Post created");
        }catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with id = %s not found".formatted(postId));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePost(@RequestParam("postId") Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.ok("Post deleted");
        }catch (PostNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with id = %S not found".formatted(postId));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }

    }

    @GetMapping("/get-by-userId/{userId}")
    public ResponseEntity<List<PostDTO>> getPostById(@PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(postService.getPostsByUserId(userId));
        }catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        try {
            return ResponseEntity.ok(postService.getAllPosts());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
