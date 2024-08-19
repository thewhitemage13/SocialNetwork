package org.thewhitemage13.interfaces;

import org.thewhitemage13.UserEvent;
import org.thewhitemage13.exception.CommentNotFoundException;
import org.thewhitemage13.exception.MediaNotFoundException;

public interface UserDeletedEventHandlerInterface {
    void userDeleted(UserEvent userEvent) throws MediaNotFoundException, CommentNotFoundException;
}
