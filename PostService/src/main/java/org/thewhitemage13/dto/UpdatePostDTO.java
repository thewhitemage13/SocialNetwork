package org.thewhitemage13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdatePostDTO {
    private String content;
    private String mediaUrl;

    @Override
    public String toString() {
        return "UpdatePostDTO{" +
                "content='" + content + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                '}';
    }
}
