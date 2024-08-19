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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentStatisticId;
    private Long numberOfCreatedComments;
    private Long numberOfDeletedComments;
    private LocalDate statisticDate;
}
