package org.thewhitemage13.interfaces;

import org.thewhitemage13.dto.UserDTO;
import java.util.List;

public interface MediaValidationServiceInterface {
    List<String> validateMedia(Long userId);
    void validatePicture(UserDTO userDTO);
}
