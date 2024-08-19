package org.thewhitemage13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thewhitemage13.entity.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findAllByPostId(Long id);
    Long countByPostId(Long id);
    void deleteAllByUserId(Long userId);
    void deleteAllByPostId(Long postId);
    List<Comment> findByPostId(Long postId);
    List<Comment> findByUserId(Long userId);
}
