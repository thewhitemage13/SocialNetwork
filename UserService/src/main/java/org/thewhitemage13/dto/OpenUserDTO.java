package org.thewhitemage13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OpenUserDTO {
    private String username;
    private String profilePictureUrl;
    private Long countPosts;
    private Long countFollowing;
    private Long countFollowers;
    private List<String> mediaPostsUrl = new ArrayList<>();

    public OpenUserDTO(String username, Long countFollowers, Long countPosts, Long countFollowing, List<String> mediaPostsUrl, String profilePictureUrl) {
        this.username = username;
        this.countFollowers = countFollowers;
        this.countPosts = countPosts;
        this.countFollowing = countFollowing;
        this.mediaPostsUrl = mediaPostsUrl;
        this.profilePictureUrl = profilePictureUrl;
    }

    @Override
    public String toString() {
        return "OpenUserDTO{" +
                "username='" + username + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                ", countPosts=" + countPosts +
                ", countFollowing=" + countFollowing +
                ", countFollowers=" + countFollowers +
                ", mediaPostsUrl=" + mediaPostsUrl +
                '}';
    }
}
