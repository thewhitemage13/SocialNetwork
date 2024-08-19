package org.thewhitemage13.interfaces;

import org.thewhitemage13.UserEvent;
import org.thewhitemage13.entity.UserStatistic;

import java.time.LocalDate;
import java.util.List;

public interface UserStatisticServiceInterface {
    List<UserStatistic> getUserStatistics();
    UserStatistic getUserStatisticByDate(LocalDate date);
    void deleteStatisticByDate(LocalDate date);
    void createUserStatistic(UserEvent userEvent);
    void remoteUserStatistic(UserEvent userEvent);
}
