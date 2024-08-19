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
public class PostStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postStatisticId;
    private Long postsCreated;
    private Long postsDeleted;
    private LocalDate statisticDate;
}
