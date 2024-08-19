package org.thewhitemage13.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes")
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "post_id", nullable = true)
    private Long postId;
    @Column(name = "comment_id", nullable = true)
    private Long commentId;
    private LocalDateTime createdAt;
}
