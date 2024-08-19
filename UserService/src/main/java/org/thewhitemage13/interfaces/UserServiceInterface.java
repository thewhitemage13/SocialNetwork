package org.thewhitemage13.interfaces;

import com.google.i18n.phonenumbers.NumberParseException;
import org.thewhitemage13.dto.GetUserDTO;
import org.thewhitemage13.dto.OpenUserDTO;
import org.thewhitemage13.dto.UserDTO;
import org.thewhitemage13.dto.UserSubscriptionDTO;
import org.thewhitemage13.exception.EmailAlreadyTakenException;
import org.thewhitemage13.exception.EmailBusyException;
import org.thewhitemage13.exception.UserNotFoundException;
import org.thewhitemage13.exception.UsernameIsBusyException;

import java.util.List;

public interface UserServiceInterface {
    void addUsers(List<UserDTO> users) throws NumberParseException;
    String getUsernameById(Long userId);
    OpenUserDTO openUser(Long userId);
    GetUserDTO getUserById(Long userId);
    List<UserSubscriptionDTO> getUsersByIds(List<Long> userIds);
    boolean userVerification(Long userId);
    void registerNewUser(UserDTO userDTO) throws UsernameIsBusyException, EmailAlreadyTakenException, EmailBusyException, NumberParseException;
    void updateUserProfile(Long userId, UserDTO userDTO) throws UsernameIsBusyException, UserNotFoundException, EmailAlreadyTakenException, EmailBusyException, NumberParseException;
    GetUserDTO getInformationAboutUser(String username) throws UserNotFoundException;
    void deleteUser(Long userId) throws UserNotFoundException;
}
