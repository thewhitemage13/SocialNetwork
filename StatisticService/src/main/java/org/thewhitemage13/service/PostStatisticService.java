package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.PostEvent;
import org.thewhitemage13.entity.PostStatistic;
import org.thewhitemage13.exception.StatisticNotFoundException;
import org.thewhitemage13.interfaces.PostStatisticServiceInterface;
import org.thewhitemage13.repository.PostStatisticRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PostStatisticService implements PostStatisticServiceInterface {
    private final PostStatisticRepository postStatisticRepository;

    @Autowired
    public PostStatisticService(PostStatisticRepository postStatisticRepository) {
        this.postStatisticRepository = postStatisticRepository;
    }

    @Override
    public List<PostStatistic> getPostStatistics() {
        return postStatisticRepository.findAll();
    }

    @Override
    public PostStatistic getStatisticByDate(LocalDate date) {
        return postStatisticRepository.getByStatisticDate(date).orElseThrow(() -> new StatisticNotFoundException("Statistic with id = %s not found".formatted(date)));
    }

    @Override
    public void deleteStatisticByDate(LocalDate date) {
        PostStatistic statistic = postStatisticRepository.getByStatisticDate(date).orElseThrow(() -> new StatisticNotFoundException("Statistic with id = %s not found".formatted(date)));
        postStatisticRepository.delete(statistic);
    }

    @Override
    public void createPostStatistic(PostEvent postEvent) {
        LocalDate today = LocalDate.now();

        PostStatistic statistic = postStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new PostStatistic();
            statistic.setStatisticDate(today);
            statistic.setPostsCreated(1L);
            statistic.setPostsDeleted(0L);
        } else {
            statistic.setPostsCreated(statistic.getPostsCreated() + 1);
        }
        postStatisticRepository.save(statistic);
    }

    @Override
    public void deletePostStatistic(PostEvent postEvent) {
        LocalDate today = LocalDate.now();

        PostStatistic statistic = postStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new PostStatistic();
            statistic.setStatisticDate(today);
            statistic.setPostsCreated(0L);
            statistic.setPostsDeleted(1L);
        } else {
            statistic.setPostsDeleted(statistic.getPostsDeleted() + 1);
            statistic.setPostsCreated(statistic.getPostsCreated() - 1);
        }
        postStatisticRepository.save(statistic);
    }
}
