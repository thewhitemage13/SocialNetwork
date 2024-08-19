package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.entity.CommentStatistic;
import org.thewhitemage13.exception.StatisticNotFoundException;
import org.thewhitemage13.interfaces.CommentStatisticServiceInterface;
import org.thewhitemage13.repository.CommentStatisticRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CommentStatisticService implements CommentStatisticServiceInterface {
    private final CommentStatisticRepository commentStatisticRepository;

    @Autowired
    public CommentStatisticService(CommentStatisticRepository commentStatisticRepository) {
        this.commentStatisticRepository = commentStatisticRepository;
    }

    @Override
    public void createCommentStatistic() {
        LocalDate today = LocalDate.now();

        CommentStatistic statistic = commentStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new CommentStatistic();
            statistic.setStatisticDate(today);
            statistic.setNumberOfCreatedComments(1L);
            statistic.setNumberOfDeletedComments(0L);
        } else {
            statistic.setNumberOfCreatedComments(statistic.getNumberOfCreatedComments() + 1);
        }
        commentStatisticRepository.save(statistic);
    }

    @Override
    public void deleteCommentStatistic() {
        LocalDate today = LocalDate.now();

        CommentStatistic statistic = commentStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new CommentStatistic();
            statistic.setStatisticDate(today);
            statistic.setNumberOfCreatedComments(0L);
            statistic.setNumberOfDeletedComments(1L);
        } else {
            statistic.setNumberOfDeletedComments(statistic.getNumberOfDeletedComments() + 1);
            statistic.setNumberOfCreatedComments(statistic.getNumberOfCreatedComments() - 1);
        }
        commentStatisticRepository.save(statistic);
    }

    @Override
    public List<CommentStatistic> showAllStatistics() {
        return commentStatisticRepository.findAll();
    }

    @Override
    public CommentStatistic showStatisticsByDate(LocalDate date) {
        return commentStatisticRepository.getByStatisticDate(date).orElseThrow(() -> new StatisticNotFoundException("Statistic with date = %s not found".formatted(date)));
    }

    @Override
    public void deleteStatisticByDate(LocalDate date) {
        CommentStatistic statistic = commentStatisticRepository.findByStatisticDate(date);
        commentStatisticRepository.delete(statistic);
    }

}
