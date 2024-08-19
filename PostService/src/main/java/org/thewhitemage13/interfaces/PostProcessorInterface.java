package org.thewhitemage13.interfaces;

import org.thewhitemage13.PostEvent;
import org.thewhitemage13.dto.PostDTO;
import org.thewhitemage13.entity.Post;

import java.util.List;

public interface PostProcessorInterface {
    List<PostDTO> getPostDTOS(List<Post> posts);
    PostEvent getPostEvent(Post deletePost);
}
