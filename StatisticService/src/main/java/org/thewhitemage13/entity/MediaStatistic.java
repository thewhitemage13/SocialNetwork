package org.thewhitemage13.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MediaStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaStatisticId;
    private Long numberOfUploadedFiles;
    private Long numberOfDeletedFiles;
    private Double totalFileSize;
    private LocalDate statisticDate;
}
