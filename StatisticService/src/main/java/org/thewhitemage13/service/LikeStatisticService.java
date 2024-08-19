package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.entity.LikeStatistic;
import org.thewhitemage13.exception.StatisticNotFoundException;
import org.thewhitemage13.interfaces.LikeStatisticServiceInterface;
import org.thewhitemage13.repository.LikeStatisticRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LikeStatisticService implements LikeStatisticServiceInterface {
    private final LikeStatisticRepository likeStatisticRepository;

    @Autowired
    public LikeStatisticService(LikeStatisticRepository likeStatisticRepository) {
        this.likeStatisticRepository = likeStatisticRepository;
    }

    @Override
    public List<LikeStatistic> getAllLikeStatistics() {
        return likeStatisticRepository.findAll();
    }

    @Override
    public LikeStatistic getLikeStatisticByDate(LocalDate date) {
        return likeStatisticRepository.getByStatisticDate(date).orElseThrow(() -> new StatisticNotFoundException("Statistic with date = %s not found".formatted(date)));
    }

    @Override
    public void deleteStatisticByDate(LocalDate date) {
        LikeStatistic statistic = likeStatisticRepository.getByStatisticDate(date).orElseThrow(() -> new StatisticNotFoundException("Statistic with date = %s not found".formatted(date)));
        likeStatisticRepository.delete(statistic);
    }

    @Override
    public void createLikePostStatistic() {
        LocalDate today = LocalDate.now();

        LikeStatistic statistic = likeStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new LikeStatistic();
            statistic.setStatisticDate(today);
            statistic.setPostLike(1L);
            statistic.setCommentLike(0L);
            statistic.setRemovePostLike(0L);
            statistic.setRemoveCommentLike(0L);
        } else {
            statistic.setPostLike(statistic.getPostLike() + 1);
        }
        likeStatisticRepository.save(statistic);

    }

    @Override
    public void deleteLikePostStatistic() {
        LocalDate today = LocalDate.now();

        LikeStatistic statistic = likeStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new LikeStatistic();
            statistic.setStatisticDate(today);
            statistic.setPostLike(0L);
            statistic.setCommentLike(0L);
            statistic.setRemovePostLike(1L);
            statistic.setRemoveCommentLike(0L);
        } else {
            statistic.setRemovePostLike(statistic.getRemovePostLike() + 1);
            statistic.setPostLike(statistic.getPostLike() - 1);
        }
        likeStatisticRepository.save(statistic);

    }

    @Override
    public void createLikeCommentStatistic() {
        LocalDate today = LocalDate.now();

        LikeStatistic statistic = likeStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new LikeStatistic();
            statistic.setStatisticDate(today);
            statistic.setPostLike(0L);
            statistic.setCommentLike(1L);
            statistic.setRemovePostLike(0L);
            statistic.setRemoveCommentLike(0L);
        } else {
            statistic.setCommentLike(statistic.getCommentLike() + 1);
        }
        likeStatisticRepository.save(statistic);

    }

    @Override
    public void deleteLikeCommentStatistic() {
        LocalDate today = LocalDate.now();

        LikeStatistic statistic = likeStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new LikeStatistic();
            statistic.setStatisticDate(today);
            statistic.setPostLike(0L);
            statistic.setCommentLike(0L);
            statistic.setRemovePostLike(0L);
            statistic.setRemoveCommentLike(1L);
        } else {
            statistic.setRemoveCommentLike(statistic.getRemoveCommentLike() + 1);
            statistic.setCommentLike(statistic.getCommentLike() - 1);
        }
        likeStatisticRepository.save(statistic);

    }
}
