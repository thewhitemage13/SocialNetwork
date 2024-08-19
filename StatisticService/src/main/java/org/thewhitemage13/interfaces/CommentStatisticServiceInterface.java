package org.thewhitemage13.interfaces;

import org.thewhitemage13.entity.CommentStatistic;

import java.time.LocalDate;
import java.util.List;

public interface CommentStatisticServiceInterface {
    void createCommentStatistic();
    void deleteCommentStatistic();
    List<CommentStatistic> showAllStatistics();
    CommentStatistic showStatisticsByDate(LocalDate date);
    void deleteStatisticByDate(LocalDate date);
}
