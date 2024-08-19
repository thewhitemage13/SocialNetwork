package org.thewhitemage13.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDto {
    private Long postId;
    private Long userId;
    private String content;
}
