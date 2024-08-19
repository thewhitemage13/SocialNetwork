package org.thewhitemage13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thewhitemage13.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findAllByUserId(Long userId);
    boolean existsById(Long postId);
    Long countByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
