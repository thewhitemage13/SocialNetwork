package org.thewhitemage13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thewhitemage13.entity.Like;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByPostId(Long postId);
    List<Like> findAllByCommentId(Long commentId);
    Long countAllByPostId(Long postId);
    void deleteAllByUserId(Long userId);
    void deleteAllByPostId(Long postId);
    void deleteAllByCommentId(Long commentId);
    List<Like> findAllByUserId(Long userId);
}
