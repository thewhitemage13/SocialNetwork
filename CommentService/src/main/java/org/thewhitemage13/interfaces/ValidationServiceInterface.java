package org.thewhitemage13.interfaces;

import org.thewhitemage13.dto.CommentCreateDto;

public interface ValidationServiceInterface {
    void validateUser(CommentCreateDto commentCreateDto);
    void validatePost(CommentCreateDto commentCreateDto);
}
