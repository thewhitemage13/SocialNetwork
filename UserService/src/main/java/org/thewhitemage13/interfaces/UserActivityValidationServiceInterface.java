package org.thewhitemage13.interfaces;

import com.google.i18n.phonenumbers.NumberParseException;
import org.thewhitemage13.dto.UserDTO;
import org.thewhitemage13.entity.User;
import org.thewhitemage13.exception.EmailBusyException;

public interface UserActivityValidationServiceInterface {
    String updateUserValidationProcessor(UserDTO userDTO, User updateUser) throws EmailBusyException, NumberParseException;
    void validateUsername(String username);
}
