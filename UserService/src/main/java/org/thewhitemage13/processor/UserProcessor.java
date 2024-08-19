package org.thewhitemage13.processor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.dto.GetUserDTO;
import org.thewhitemage13.dto.OpenUserDTO;
import org.thewhitemage13.dto.UserDTO;
import org.thewhitemage13.entity.User;
import org.thewhitemage13.interfaces.UserProcessorInterface;

import java.util.List;

@Component
public class UserProcessor implements UserProcessorInterface {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void userCreateProcessor(UserDTO userDTO, User user, String phoneNumber) {
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setSurname(userDTO.getSurname());
        user.setProfilePictureUrl(userDTO.getProfilePictureUrl());
        user.setRegion(userDTO.getRegion());
        user.setPhoneNumber(phoneNumber);
    }

    @Override
    public GetUserDTO getGetUserDTO(User user) {
        return new GetUserDTO
                (
                        user.getUsername(),
                        user.getPhoneNumber(),
                        user.getRegion(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getSurname(),
                        user.getLastName(),
                        user.getProfilePictureUrl()
                );
    }

    @Override
    public UserEvent getUserEvent(User user) {
        return new UserEvent
                (
                        user.getUserId(),
                        user.getUsername(),
                        user.getPhoneNumber(),
                        user.getRegion(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getSurname(),
                        user.getLastName(),
                        user.getProfilePictureUrl()
                );
    }

    @Override
    public OpenUserDTO getOpenUserDTO(String userName, String profilePictureUrl, List<String> mediaPostsUrl, Long countFollowing, Long countFollowers, Long countPosts) {
        OpenUserDTO openUserDTO = new OpenUserDTO();
        openUserDTO.setUsername(userName);
        openUserDTO.setProfilePictureUrl(profilePictureUrl);
        openUserDTO.setMediaPostsUrl(mediaPostsUrl);
        openUserDTO.setCountFollowing(countFollowing);
        openUserDTO.setCountFollowers(countFollowers);
        openUserDTO.setCountPosts(countPosts);
        return openUserDTO;
    }

}
