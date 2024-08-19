package org.thewhitemage13.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.LikeEvent;
import org.thewhitemage13.dto.CreateLikeComment;
import org.thewhitemage13.dto.CreateLikePost;
import org.thewhitemage13.entity.Like;
import org.thewhitemage13.exception.LikeNotFoundException;
import org.thewhitemage13.interfaces.LikeServiceInterface;
import org.thewhitemage13.repository.LikeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LikeService implements LikeServiceInterface {
    private final LikeRepository likeRepository;
    private final ValidationService validationService;
    private final KafkaTemplate<Long, Object> kafkaTemplate;

    public LikeService(LikeRepository likeRepository, ValidationService validationService, KafkaTemplate<Long, Object> kafkaTemplate) {
        this.likeRepository = likeRepository;
        this.validationService = validationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void deleteAllByCommentId(Long commentId) throws LikeNotFoundException {
        List<Like> deleteAll = likeRepository.findAllByCommentId(commentId);
        for (Like like : deleteAll) {
            deleteLike(like.getLikeId());
        }
    }

    @Override
    public Long getPostLikeCount(Long postId) {
        return likeRepository.countAllByPostId(postId);
    }

    @Override
    public void deleteAllByPostId(Long postId) throws LikeNotFoundException {
        List<Like> deleteAll = likeRepository.findAllByPostId(postId);
        for (Like like : deleteAll) {
            deleteLike(like.getLikeId());
        }
    }

    @Override
    public void deleteAllByUserId(Long userId) throws LikeNotFoundException {
        List<Like> deleteLike = likeRepository.findAllByUserId(userId);
        for (Like like : deleteLike) {
            deleteLike(like.getLikeId());
        }

    }

    @Override
    public void postLike(CreateLikePost createLikePost) {
        validationService.validatePostLike(createLikePost);

        Like like = new Like();

        like.setPostId(createLikePost.getPostId());
        like.setUserId(createLikePost.getUserId());
        like.setCreatedAt(LocalDateTime.now());

        likeRepository.save(like);

        LikeEvent likeEvent = new LikeEvent
                (
                        like.getLikeId(),
                        like.getUserId(),
                        like.getPostId(),
                        like.getCommentId(),
                        like.getCreatedAt()
                );

        kafkaTemplate.send("post.like.created",likeEvent.getLikeId(), likeEvent);
    }

    @Override
    public void commentLike(CreateLikeComment createLikeComment) {
        validationService.validateCommentLike(createLikeComment);

        Like like = new Like();

        like.setCommentId(createLikeComment.getCommentId());
        like.setUserId(createLikeComment.getUserId());
        like.setCreatedAt(LocalDateTime.now());

        likeRepository.save(like);

        LikeEvent likeEvent = new LikeEvent
                (
                        like.getLikeId(),
                        like.getUserId(),
                        like.getPostId(),
                        like.getCommentId(),
                        like.getCreatedAt()
                );

        kafkaTemplate.send("comment.like.created",likeEvent.getLikeId(), likeEvent);
    }

    @Override
    public void deleteLike(Long likeId) throws LikeNotFoundException {
        Like deleteLike = likeRepository.findById(likeId).orElseThrow(() -> new LikeNotFoundException("Like with id = %s not found".formatted(likeId)));
        likeRepository.delete(deleteLike);

        LikeEvent likeEvent = new LikeEvent
                (
                        deleteLike.getLikeId(),
                        deleteLike.getUserId(),
                        deleteLike.getPostId(),
                        deleteLike.getCommentId(),
                        deleteLike.getCreatedAt()
                );

        if (deleteLike.getPostId() == null)
        {
            kafkaTemplate.send("comment.like.deleted", deleteLike.getCommentId(), likeEvent);
        }
        else {
            kafkaTemplate.send("post.like.deleted", deleteLike.getCommentId(), likeEvent);
        }
    }

    @Override
    public Long showPostLikeSum(Long postId) {
        List<Like> postLike = likeRepository.findAllByPostId(postId);
        return (long) postLike.size();
    }

    @Override
    public Long showCommentLikeSum(Long commentId) {
        List<Like> commentLike = likeRepository.findAllByCommentId(commentId);
        return (long) commentLike.size();
    }

}
