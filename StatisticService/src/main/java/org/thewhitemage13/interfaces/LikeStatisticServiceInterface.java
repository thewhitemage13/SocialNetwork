package org.thewhitemage13.interfaces;

import org.thewhitemage13.entity.LikeStatistic;

import java.time.LocalDate;
import java.util.List;

public interface LikeStatisticServiceInterface {
    List<LikeStatistic> getAllLikeStatistics();
    LikeStatistic getLikeStatisticByDate(LocalDate date);
    void deleteStatisticByDate(LocalDate date);
    void createLikePostStatistic();
    void deleteLikePostStatistic();
    void createLikeCommentStatistic();
    void deleteLikeCommentStatistic();
}
