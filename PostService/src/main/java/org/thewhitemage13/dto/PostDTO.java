package org.thewhitemage13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {
    private Long userId;
    private String content;
    private String mediaUrl;

    @Override
    public String toString() {
        return "PostDTO{" +
                "userId=" + userId +
                ", content='" + content + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                '}';
    }
}
