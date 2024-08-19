package org.thewhitemage13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thewhitemage13.entity.Media;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    boolean existsByUrl(String url);
    void deleteAllByUserId(Long userId);
    List<Media> findAllByUserId(Long userId);
}
