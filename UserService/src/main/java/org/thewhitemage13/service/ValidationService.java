package org.thewhitemage13.service;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thewhitemage13.dto.UserDTO;
import org.thewhitemage13.entity.User;
import org.thewhitemage13.interfaces.ValidationServiceInterface;

import java.util.List;

@Service
public class ValidationService implements ValidationServiceInterface {
    private final EmailValidationService emailValidationService;
    private final FollowerValidationService followerValidationService;
    private final MediaValidationService mediaValidationService;
    private final PasswordValidationService passwordValidationService;
    private final PhoneValidationService phoneValidationService;
    private final PostValidationService postValidationService;
    private final UserActivityValidationService userActivityValidationService;

    @Autowired
    public ValidationService(EmailValidationService emailValidationService, FollowerValidationService followerValidationService, MediaValidationService mediaValidationService, PasswordValidationService passwordValidationService, PhoneValidationService phoneValidationService, PostValidationService postValidationService, UserActivityValidationService userActivityValidationService) {
        this.emailValidationService = emailValidationService;
        this.followerValidationService = followerValidationService;
        this.mediaValidationService = mediaValidationService;
        this.passwordValidationService = passwordValidationService;
        this.phoneValidationService = phoneValidationService;
        this.postValidationService = postValidationService;
        this.userActivityValidationService = userActivityValidationService;
    }

    @Override
    public void validateUsername(String username) {
        userActivityValidationService.validateUsername(username);
    }

    @Override
    public String validateUpdateUser(UserDTO userDTO, User updateUser) throws NumberParseException {
        return userActivityValidationService.updateUserValidationProcessor(userDTO, updateUser);
    }

    @Override
    public Long validatePost(Long userId) {
        return postValidationService.countPostValidation(userId);
    }

    @Override
    public String validateUpdatePhoneNumber(String phoneNum, String region) throws NumberParseException {
        return phoneValidationService.validateUpdatePhoneNumber(phoneNum, region);
    }

    @Override
    public String validatePhoneNumber(String phoneNumber, String region) throws NumberParseException {
        return phoneValidationService.validatePhoneNumber(phoneNumber, region);
    }

    @Override
    public void validatePassword(String password) {
        passwordValidationService.validatePassword(password);
    }

    @Override
    public void validatePicture(UserDTO userDTO) {
        mediaValidationService.validatePicture(userDTO);
    }

    @Override
    public List<String> validateMedia(Long userId) {
        return mediaValidationService.validateMedia(userId);
    }

    @Override
    public Long validateFollowing(Long userId) {
        return followerValidationService.countFollowingValidation(userId);
    }

    @Override
    public Long validateFollowers(Long userId) {
        return followerValidationService.countFollowersValidation(userId);
    }

    @Override
    public void validateEmail(String email) {
        emailValidationService.validateEmail(email);
    }

    @Override
    public void validateUpdateEmail(String email) {
        emailValidationService.validateUpdateEmail(email);
    }
}
