package org.thewhitemage13.interfaces;

import org.thewhitemage13.PostEvent;
import org.thewhitemage13.entity.PostStatistic;

import java.time.LocalDate;
import java.util.List;

public interface PostStatisticServiceInterface {
    List<PostStatistic> getPostStatistics();
    PostStatistic getStatisticByDate(LocalDate date);
    void deleteStatisticByDate(LocalDate date);
    void createPostStatistic(PostEvent postEvent);
    void deletePostStatistic(PostEvent postEvent);
}
