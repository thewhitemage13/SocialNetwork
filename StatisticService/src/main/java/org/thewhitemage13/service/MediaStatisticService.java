package org.thewhitemage13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.MediaEvent;
import org.thewhitemage13.entity.MediaStatistic;
import org.thewhitemage13.exception.StatisticNotFoundException;
import org.thewhitemage13.interfaces.MediaStatisticServiceInterface;
import org.thewhitemage13.repository.MediaStatisticRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MediaStatisticService implements MediaStatisticServiceInterface {
    private final MediaStatisticRepository mediaStatisticRepository;

    @Autowired
    public MediaStatisticService(MediaStatisticRepository mediaStatisticRepository) {
        this.mediaStatisticRepository = mediaStatisticRepository;
    }

    @Override
    public List<MediaStatistic> getAllMediaStatistics() {
        return mediaStatisticRepository.findAll();
    }

    @Override
    public MediaStatistic getMediaStatisticByDate(LocalDate date) {
        return mediaStatisticRepository.getByStatisticDate(date).orElseThrow(() -> new StatisticNotFoundException("Statistic with id = %s not found".formatted(date)));
    }

    @Override
    public void deleteStatisticByDate(LocalDate date) {
        MediaStatistic statistic = mediaStatisticRepository.getByStatisticDate(date).orElseThrow(() -> new StatisticNotFoundException("Statistic with id = %s not found".formatted(date)));
        mediaStatisticRepository.delete(statistic);
    }

    @Override
    public void uploadMediaStatistic(MediaEvent mediaEvent) {
        LocalDate today = LocalDate.now();

        MediaStatistic statistic = mediaStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new MediaStatistic();
            statistic.setStatisticDate(today);
            statistic.setNumberOfUploadedFiles(1L);
            statistic.setTotalFileSize(mediaEvent.getFileSize());
            statistic.setNumberOfDeletedFiles(0L);
        } else {
            statistic.setNumberOfUploadedFiles(statistic.getNumberOfUploadedFiles() + 1);
            statistic.setTotalFileSize(statistic.getTotalFileSize() + mediaEvent.getFileSize());
        }
        mediaStatisticRepository.save(statistic);
    }

    @Override
    public void deleteMediaStatistic(MediaEvent mediaEvent) {
        LocalDate today = LocalDate.now();

        MediaStatistic statistic = mediaStatisticRepository.findByStatisticDate(today);

        if (statistic == null) {
            statistic = new MediaStatistic();
            statistic.setStatisticDate(today);
            statistic.setNumberOfUploadedFiles(0L);
            statistic.setTotalFileSize(0.0);
            statistic.setNumberOfDeletedFiles(1L);
        } else {
            statistic.setNumberOfDeletedFiles(statistic.getNumberOfDeletedFiles() + 1);
            statistic.setTotalFileSize(statistic.getTotalFileSize() - mediaEvent.getFileSize());
        }
        mediaStatisticRepository.save(statistic);
    }
}
