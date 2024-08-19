package org.thewhitemage13.service;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thewhitemage13.dto.UserDTO;
import org.thewhitemage13.entity.User;
import org.thewhitemage13.exception.EmailBusyException;
import org.thewhitemage13.exception.UsernameIsBusyException;
import org.thewhitemage13.interfaces.UserActivityValidationServiceInterface;
import org.thewhitemage13.repository.UserRepository;

@Service
public class UserActivityValidationService implements UserActivityValidationServiceInterface {
    private final UserRepository userRepository;
    private final EmailValidationService emailValidationService;
    private final PhoneValidationService phoneValidationService;

    @Autowired
    public UserActivityValidationService(UserRepository userRepository, EmailValidationService emailValidationService, PhoneValidationService phoneValidationService) {
        this.userRepository = userRepository;
        this.emailValidationService = emailValidationService;
        this.phoneValidationService = phoneValidationService;
    }

    @Override
    public String updateUserValidationProcessor(UserDTO userDTO, User updateUser) throws EmailBusyException, NumberParseException {

        if (!updateUser.getEmail().equals(userDTO.getEmail())) {
            emailValidationService.validateEmail(userDTO.getEmail());
        } else {
            emailValidationService.validateUpdateEmail(userDTO.getEmail());
        }

        String phoneNum;

        if (!updateUser.getPhoneNumber().equals(userDTO.getPhoneNumber())) {
            phoneNum = phoneValidationService.validatePhoneNumber(userDTO.getPhoneNumber(), userDTO.getRegion());
        } else {
            phoneNum = phoneValidationService.validateUpdatePhoneNumber(userDTO.getPhoneNumber(), userDTO.getRegion());
        }

        if (!updateUser.getUsername().equals(userDTO.getUsername())) {
            validateUsername(userDTO.getUsername());
        }

        return phoneNum;
    }

    @Override
    public void validateUsername(String username) {
        boolean user = userRepository.existsUserByUsername(username);
        if (user) {
            throw new UsernameIsBusyException("User with username = %s is busy".formatted(username));
        }
    }
}
