package org.thewhitemage13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenPostDTO {
    private String username;
    private String mediaUrl;
    private String content;
    private Long likes;
    private Long comments;

    @Override
    public String toString() {
        return "OpenPostDTO{" +
                "username='" + username + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }
}
