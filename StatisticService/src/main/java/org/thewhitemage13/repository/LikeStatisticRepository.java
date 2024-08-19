package org.thewhitemage13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thewhitemage13.entity.LikeStatistic;

import java.time.LocalDate;
import java.util.Optional;

public interface LikeStatisticRepository extends JpaRepository<LikeStatistic, Long> {
    LikeStatistic findByStatisticDate(LocalDate statisticDate);
    Optional<LikeStatistic> getByStatisticDate(LocalDate statisticDate);
}
