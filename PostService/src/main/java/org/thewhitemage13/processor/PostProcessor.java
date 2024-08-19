package org.thewhitemage13.processor;

import org.springframework.stereotype.Component;
import org.thewhitemage13.PostEvent;
import org.thewhitemage13.dto.PostDTO;
import org.thewhitemage13.entity.Post;
import org.thewhitemage13.interfaces.PostProcessorInterface;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostProcessor implements PostProcessorInterface {

    @Override
    public List<PostDTO> getPostDTOS(List<Post> posts) {
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setMediaUrl(post.getMediaUrl());
            postDTO.setContent(post.getContent());
            postDTO.setUserId(post.getUserId());
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    @Override
    public PostEvent getPostEvent(Post deletePost) {
        return new PostEvent
                (
                        deletePost.getPostId(),
                        deletePost.getUserId(),
                        deletePost.getContent(),
                        deletePost.getMediaUrl(),
                        deletePost.getCreatedAt(),
                        deletePost.getUpdatedAt()
                );
    }
}
