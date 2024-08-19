package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.PostEvent;
import org.thewhitemage13.clients.UserClient;
import org.thewhitemage13.dto.OpenPostDTO;
import org.thewhitemage13.dto.PostDTO;
import org.thewhitemage13.dto.UpdatePostDTO;
import org.thewhitemage13.entity.Post;
import org.thewhitemage13.exception.PostNotFoundException;
import org.thewhitemage13.exception.UserNotFoundException;
import org.thewhitemage13.interfaces.PostServiceInterface;
import org.thewhitemage13.processor.PostProcessor;
import org.thewhitemage13.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostService implements PostServiceInterface {
    private final PostRepository postRepository;
    private final ValidationService validationService;
    private final UserClient userClient;
    private final KafkaTemplate<Long, Object> kafkaTemplate;
    private final PostProcessor postProcessor;

    @Autowired
    public PostService(PostRepository postRepository, ValidationService validationService, KafkaTemplate<Long, Object> kafkaTemplate, UserClient userClient, PostProcessor postProcessor) {
        this.postRepository = postRepository;
        this.validationService = validationService;
        this.kafkaTemplate = kafkaTemplate;
        this.userClient = userClient;
        this.postProcessor = postProcessor;
    }

    @Override
    public Long getUserIdByPostId(Long postId) {
        return postRepository.findById(postId).get().getUserId();
    }

    @Override
    public void deleteAllByUserId(Long userId) throws PostNotFoundException {
        List<Post> deleteAll = postRepository.findAllByUserId(userId).orElseThrow(() -> new PostNotFoundException("Post with user id = %s not found".formatted(userId)));

        for (Post post : deleteAll) {
            deletePost(post.getPostId());
        }
    }

    @Override
    public List<String> getUrlsByUserId(Long userId) {
        List<Post> get = postRepository.findAllByUserId(userId).orElseThrow(() -> new UserNotFoundException("User with id = %s not found".formatted(userId)));
        List<String> urls = new ArrayList<>();
        for (Post post : get) {
            urls.add(post.getMediaUrl());
        }
        return urls;
    }

    @Override
    public Long getCountPostByUserId(Long userId) {
        return postRepository.countByUserId(userId);
    }

    @Override
    public List<OpenPostDTO> openAllPostsByUserId(Long userId) {
        List<Post> openAll = postRepository.findAllByUserId(userId).orElseThrow(() -> new UserNotFoundException("User with id = %s not found".formatted(userId)));
        List<OpenPostDTO> openPosts = new ArrayList<>();
        for (Post post : openAll) {
            OpenPostDTO openPostDTO = new OpenPostDTO();
            openPostDTO.setMediaUrl(post.getMediaUrl());

            Long likes = validationService.validateLike(post.getPostId());

            Long comments = validationService.validateComment(post.getPostId());

            ResponseEntity<String> name = userClient.getUserNameById(post.getUserId());
            String username = name.getBody();
            openPostDTO.setUsername(username);
            openPostDTO.setLikes(likes);
            openPostDTO.setComments(comments);
            openPostDTO.setContent(post.getContent());
            openPosts.add(openPostDTO);
        }
        return openPosts;
    }

    @Override
    public OpenPostDTO openPost(Long postId) throws PostNotFoundException {
        Post open = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post with id = %s not found".formatted(postId)));

        String mediaUrl = open.getMediaUrl();
        String content = open.getContent();

        Long likes = validationService.validateLike(postId);

        Long comments = validationService.validateComment(postId);


        ResponseEntity<String> name = userClient.getUserNameById(open.getUserId());
        String username = name.getBody();

        OpenPostDTO openPostDTO = new OpenPostDTO();
        openPostDTO.setMediaUrl(mediaUrl);
        openPostDTO.setContent(content);
        openPostDTO.setLikes(likes);
        openPostDTO.setComments(comments);
        openPostDTO.setUsername(username);

        return openPostDTO;
    }

    @Override
    public boolean postVerification (Long postId) {
        return postRepository.existsById(postId);
    }

    @Override
    public void createPost(PostDTO postDTO) {
        validationService.validateMedia(postDTO);
        validationService.validateUser(postDTO);

        Post post = new Post
                (
                        postDTO.getUserId(),
                        postDTO.getContent(),
                        postDTO.getMediaUrl(),
                        LocalDateTime.now()
                );

        postRepository.save(post);

        PostEvent postEvent = postProcessor.getPostEvent(post);

        kafkaTemplate.send("post.created", post.getPostId(), postEvent);

    }

    @Override
    public void updatePost(Long userId, UpdatePostDTO updatePostDTO) throws PostNotFoundException {
        Post update = postRepository.findById(userId).orElseThrow(() -> new PostNotFoundException("Post with id = %s not found".formatted(userId)));

        update.setContent(updatePostDTO.getContent());
        update.setMediaUrl(updatePostDTO.getMediaUrl());
        update.setUpdatedAt(LocalDateTime.now());
        postRepository.save(update);

        PostEvent postEvent = postProcessor.getPostEvent(update);

        kafkaTemplate.send("post.updated", update.getPostId(), postEvent);

    }

    @Override
    public void deletePost(Long postId) throws PostNotFoundException {
        Post deletePost = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("Post with id = %s not found".formatted(postId)));
        postRepository.delete(deletePost);

        PostEvent postEvent = postProcessor.getPostEvent(deletePost);

        kafkaTemplate.send("post.deleted", deletePost.getPostId(), postEvent);

    }

    @Override
    public List<PostDTO> getPostsByUserId(Long userId) throws PostNotFoundException {
        List<Post> post = postRepository.findAllByUserId(userId).orElseThrow(() -> new PostNotFoundException("Posts with user id = %s is not found".formatted(userId)));
        return postProcessor.getPostDTOS(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        System.out.println(posts);
        return postProcessor.getPostDTOS(posts);
    }
}
