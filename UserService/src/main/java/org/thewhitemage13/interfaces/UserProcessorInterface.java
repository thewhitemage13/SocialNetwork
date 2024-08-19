package org.thewhitemage13.interfaces;

import org.thewhitemage13.UserEvent;
import org.thewhitemage13.dto.GetUserDTO;
import org.thewhitemage13.dto.OpenUserDTO;
import org.thewhitemage13.dto.UserDTO;
import org.thewhitemage13.entity.User;

import java.util.List;

public interface UserProcessorInterface {
    void userCreateProcessor(UserDTO userDTO, User user, String phoneNumber);
    GetUserDTO getGetUserDTO(User user);
    UserEvent getUserEvent(User user);
    OpenUserDTO getOpenUserDTO(String userName, String profilePictureUrl, List<String> mediaPostsUrl, Long countFollowing, Long countFollowers, Long countPosts);
}
