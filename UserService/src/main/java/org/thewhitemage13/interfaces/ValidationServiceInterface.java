package org.thewhitemage13.interfaces;

import com.google.i18n.phonenumbers.NumberParseException;
import org.thewhitemage13.dto.UserDTO;
import org.thewhitemage13.entity.User;

import java.util.List;

public interface ValidationServiceInterface {
    void validateUsername(String username);
    String validateUpdateUser(UserDTO userDTO, User updateUser) throws NumberParseException;
    Long validatePost(Long userId);
    String validateUpdatePhoneNumber(String phoneNum, String region) throws NumberParseException;
    String validatePhoneNumber(String phoneNumber, String region) throws NumberParseException;
    void validatePassword(String password);
    void validatePicture(UserDTO userDTO);
    List<String> validateMedia(Long userId);
    Long validateFollowing(Long userId);
    Long validateFollowers(Long userId);
    void validateEmail(String email);
    void validateUpdateEmail(String email);
}
