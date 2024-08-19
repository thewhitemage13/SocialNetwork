package org.thewhitemage13.interfaces;

import org.thewhitemage13.dto.CommentCreateDto;
import org.thewhitemage13.entity.Comment;
import org.thewhitemage13.exception.CommentNotFoundException;

import java.util.List;

public interface CommentServiceInterface {
    boolean commentVerification(Long commentId);
    void addComment(CommentCreateDto commentCreateDto);
    void updateComment(Long commentId ,CommentCreateDto commentCreateDto) throws CommentNotFoundException;
    void deleteComment(Long commentId) throws CommentNotFoundException;
    List<Comment> getAllByPostId(Long postId) throws CommentNotFoundException;
    Long getUserIdByCommentId(Long commentId);
    void deleteAllByPostId(Long postId) throws CommentNotFoundException;
    Long getCountOfCommentsByPostId(Long postId);
    void deleteAllByUserId(Long userId) throws CommentNotFoundException;
}
