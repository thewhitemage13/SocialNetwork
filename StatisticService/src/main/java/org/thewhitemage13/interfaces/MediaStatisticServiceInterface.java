package org.thewhitemage13.interfaces;

import org.thewhitemage13.MediaEvent;
import org.thewhitemage13.entity.MediaStatistic;

import java.time.LocalDate;
import java.util.List;

public interface MediaStatisticServiceInterface {
    List<MediaStatistic> getAllMediaStatistics();
    MediaStatistic getMediaStatisticByDate(LocalDate date);
    void deleteStatisticByDate(LocalDate date);
    void uploadMediaStatistic(MediaEvent mediaEvent);
    void deleteMediaStatistic(MediaEvent mediaEvent);
}
