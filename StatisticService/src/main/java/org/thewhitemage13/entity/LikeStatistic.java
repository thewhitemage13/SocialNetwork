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
public class LikeStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeStatisticId;
    private Long commentLike;
    private Long postLike;
    private Long removePostLike;
    private Long removeCommentLike;
    private LocalDate statisticDate;
}
