package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.entity.UserStatistic;
import org.thewhitemage13.exception.StatisticNotFoundException;
import org.thewhitemage13.interfaces.UserStatisticServiceInterface;
import org.thewhitemage13.repository.UserStatisticRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserStatisticService implements UserStatisticServiceInterface {
    private final UserStatisticRepository userStatisticRepository;

    @Autowired
    public UserStatisticService(UserStatisticRepository userStatisticRepository) {
        this.userStatisticRepository = userStatisticRepository;
    }

    @Override
    public List<UserStatistic> getUserStatistics() {
        return userStatisticRepository.findAll();
    }

    @Override
    public UserStatistic getUserStatisticByDate(LocalDate date) {
        return userStatisticRepository.getByStatisticDate(date).orElseThrow(() -> new StatisticNotFoundException("Statistic with id = %s not found".formatted(date)));
    }

    @Override
    public void deleteStatisticByDate(LocalDate date) {
        UserStatistic statistic = userStatisticRepository.getByStatisticDate(date).orElseThrow(() -> new StatisticNotFoundException("Statistic with id = %s not found".formatted(date)));
        userStatisticRepository.delete(statistic);
    }

    @Override
    public void createUserStatistic(UserEvent userEvent) {
        LocalDate today = LocalDate.now();

        UserStatistic statistic = userStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new UserStatistic();
            statistic.setStatisticDate(today);
            statistic.setNewUsers(1L);
            statistic.setRemoteUsers(0L);
        } else {
            statistic.setNewUsers(statistic.getNewUsers() + 1);
        }
        userStatisticRepository.save(statistic);

    }

    @Override
    public void remoteUserStatistic(UserEvent userEvent) {
        LocalDate today = LocalDate.now();

        UserStatistic statistic = userStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new UserStatistic();
            statistic.setStatisticDate(today);
            statistic.setNewUsers(0L);
            statistic.setRemoteUsers(1L);
        } else {
            statistic.setRemoteUsers(statistic.getRemoteUsers() + 1);
            statistic.setNewUsers(statistic.getNewUsers() - 1);
        }
        userStatisticRepository.save(statistic);

    }

}
