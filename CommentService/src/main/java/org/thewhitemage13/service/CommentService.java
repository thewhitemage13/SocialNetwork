package org.thewhitemage13.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.CommentEvent;
import org.thewhitemage13.dto.CommentCreateDto;
import org.thewhitemage13.entity.Comment;
import org.thewhitemage13.exception.CommentNotFoundException;
import org.thewhitemage13.interfaces.CommentServiceInterface;
import org.thewhitemage13.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentService implements CommentServiceInterface {
    private final CommentRepository commentRepository;
    private final ValidationService validationService;
    private final KafkaTemplate<Long, Object> kafkaTemplate;

    public CommentService(CommentRepository commentRepository, ValidationService validationService, KafkaTemplate<Long, Object> kafkaTemplate) {
        this.commentRepository = commentRepository;
        this.validationService = validationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Long getUserIdByCommentId(Long commentId) {
        return commentRepository.findById(commentId).get().getUserId();
    }

    public void deleteAllByPostId(Long postId) throws CommentNotFoundException {
        List<Comment> deleteAll = commentRepository.findByPostId(postId);

        for (Comment comment : deleteAll) {
            deleteComment(comment.getCommentId());
        }
    }

    public Long getCountOfCommentsByPostId(Long postId) {
        return commentRepository.countByPostId(postId);
    }

    public void deleteAllByUserId(Long userId) throws CommentNotFoundException {
        List<Comment> deleteAll = commentRepository.findByUserId(userId);

        for (Comment comment : deleteAll) {
            deleteComment(comment.getCommentId());
        }
    }

    @Override
    public boolean commentVerification(Long commentId) {
        return commentRepository.existsById(commentId);
    }

    @Override
    public void addComment(CommentCreateDto commentCreateDto) {
        validationService.validatePost(commentCreateDto);
        validationService.validateUser(commentCreateDto);

        Comment comment = new Comment();

        comment.setPostId(commentCreateDto.getPostId());
        comment.setContent(commentCreateDto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUserId(commentCreateDto.getUserId());

        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);

        CommentEvent commentEvent = new CommentEvent
                (
                        comment.getCommentId(),
                        comment.getPostId(),
                        comment.getUserId(),
                        comment.getContent(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
                );

        kafkaTemplate.send("comment.created", comment.getCommentId(), commentEvent);

    }

    @Override
    public void updateComment(Long commentId ,CommentCreateDto commentCreateDto) throws CommentNotFoundException {
        Comment update = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with id = %s not found".formatted(commentId)));

        update.setUpdatedAt(LocalDateTime.now());
        update.setContent(commentCreateDto.getContent());

        commentRepository.save(update);

        CommentEvent commentEvent = new CommentEvent
                (
                        update.getCommentId(),
                        update.getPostId(),
                        update.getUserId(),
                        update.getContent(),
                        update.getCreatedAt(),
                        update.getUpdatedAt()
                );

        kafkaTemplate.send("comment.updated", update.getCommentId(), commentEvent);
    }

    @Override
    public void deleteComment(Long commentId) throws CommentNotFoundException {
        Comment deleteComment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with id = %s not found".formatted(commentId)));
        commentRepository.delete(deleteComment);

        CommentEvent commentEvent = new CommentEvent
                (
                        deleteComment.getCommentId(),
                        deleteComment.getPostId(),
                        deleteComment.getUserId(),
                        deleteComment.getContent(),
                        deleteComment.getCreatedAt(),
                        deleteComment.getUpdatedAt()
                );

        kafkaTemplate.send("comment.deleted", deleteComment.getCommentId(), commentEvent);

    }

    @Override
    public List<Comment> getAllByPostId(Long postId) throws CommentNotFoundException {
        return commentRepository.findAllByPostId(postId).orElseThrow(() -> new CommentNotFoundException("Comment with post id = %s not found".formatted(postId)));
    }
}
