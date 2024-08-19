package org.thewhitemage13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thewhitemage13.entity.PostStatistic;

import java.time.LocalDate;
import java.util.Optional;

public interface PostStatisticRepository extends JpaRepository<PostStatistic, Long> {
    PostStatistic findByStatisticDate(LocalDate statisticDate);
    Optional<PostStatistic> getByStatisticDate(LocalDate statisticDate);
}
