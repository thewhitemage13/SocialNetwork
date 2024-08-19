package org.thewhitemage13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thewhitemage13.entity.MediaStatistic;

import java.time.LocalDate;
import java.util.Optional;

public interface MediaStatisticRepository extends JpaRepository<MediaStatistic, Long> {
    MediaStatistic findByStatisticDate(LocalDate statisticDate);
    Optional<MediaStatistic> getByStatisticDate(LocalDate statisticDate);
}
