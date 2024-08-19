package org.thewhitemage13.interfaces;

import org.thewhitemage13.dto.PostDTO;

public interface ValidationServiceInterface {
    Long validateComment(Long postId);
    Long validateLike(Long postId);
    void validateMedia(PostDTO postDTO);
    void validateUser(PostDTO postDTO);
}
