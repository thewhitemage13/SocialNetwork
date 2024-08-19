package org.thewhitemage13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thewhitemage13.entity.UserStatistic;

import java.time.LocalDate;
import java.util.Optional;

public interface UserStatisticRepository extends JpaRepository<UserStatistic, Long> {
    UserStatistic findByStatisticDate(LocalDate statisticDate);
    Optional<UserStatistic> getByStatisticDate(LocalDate statisticDate);
}
