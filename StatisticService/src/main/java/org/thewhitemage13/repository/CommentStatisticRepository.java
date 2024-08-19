package org.thewhitemage13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thewhitemage13.entity.CommentStatistic;

import java.time.LocalDate;
import java.util.Optional;

public interface CommentStatisticRepository extends JpaRepository<CommentStatistic, Long> {
    CommentStatistic findByStatisticDate(LocalDate statisticDate);
    void deleteByStatisticDate(LocalDate statisticDate);
    Optional<CommentStatistic> getByStatisticDate(LocalDate statisticDate);
}
