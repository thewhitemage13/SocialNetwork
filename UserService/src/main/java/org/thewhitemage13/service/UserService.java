package org.thewhitemage13.service;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.dto.GetUserDTO;
import org.thewhitemage13.dto.OpenUserDTO;
import org.thewhitemage13.dto.UserDTO;
import org.thewhitemage13.dto.UserSubscriptionDTO;
import org.thewhitemage13.entity.User;
import org.thewhitemage13.exception.EmailAlreadyTakenException;
import org.thewhitemage13.exception.EmailBusyException;
import org.thewhitemage13.exception.UserNotFoundException;
import org.thewhitemage13.exception.UsernameIsBusyException;
import org.thewhitemage13.interfaces.UserServiceInterface;
import org.thewhitemage13.processor.UserProcessor;
import org.thewhitemage13.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final UserProcessor userProcessor;
    private final KafkaTemplate<Long, Object> kafkaTemplate;

    @Autowired
    public UserService(UserRepository userRepository, ValidationService validationService, KafkaTemplate<Long, Object> kafkaTemplate, UserProcessor userProcessor) {
        this.userRepository = userRepository;
        this.validationService = validationService;
        this.kafkaTemplate = kafkaTemplate;
        this.userProcessor = userProcessor;
    }

    @Override
    public void addUsers(List<UserDTO> users) throws NumberParseException {
        for (UserDTO user : users) {
            registerNewUser(user);
        }
    }

    @Override
    public String getUsernameById(Long userId) {
        User get = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = %s not found".formatted(userId)));
        return get.getUsername();
    }

    @Override
    public GetUserDTO getUserById(Long userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = %s not found".formatted(userId)));
        return userProcessor.getGetUserDTO(user);
    }

    @Override
    public boolean userVerification(Long userId) {
        return userRepository.existsUserByUserId(userId);
    }

    @Override
    public GetUserDTO getInformationAboutUser(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("User with username = %s not found".formatted(username)));
        return userProcessor.getGetUserDTO(user);
    }

    @Override
    public void deleteUser(Long userId) throws UserNotFoundException {
        User user = userRepository
                .findById(userId).orElseThrow(()-> new UserNotFoundException("User with id = %s not found".formatted(userId)));

        UserEvent userEvent = userProcessor.getUserEvent(user);

        userRepository.delete(user);

        kafkaTemplate.send("user.deleted", user.getUserId(), userEvent);
    }

    @Override
    public List<UserSubscriptionDTO> getUsersByIds(List<Long> userIds) {
        List<User> users = userRepository.findAllById(userIds);
        List<UserSubscriptionDTO> subscriptions = new ArrayList<>();

        for (int i = 0; i < userIds.size(); i++) {
            UserSubscriptionDTO userSubscriptionDTO = new UserSubscriptionDTO();

            userSubscriptionDTO.setUsername(users.get(i).getUsername());
            userSubscriptionDTO.setUserId(users.get(i).getUserId());
            subscriptions.add(userSubscriptionDTO);
        }

        return subscriptions;
    }

    @Override
    public OpenUserDTO openUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = %s not found".formatted(userId)));

        String userName = user.getUsername();
        String profilePictureUrl = user.getProfilePictureUrl();

        List<String> mediaPostsUrl = validationService.validateMedia(userId);
        Long countFollowing = validationService.validateFollowing(userId);;
        Long countFollowers = validationService.validateFollowers(userId);
        Long countPosts = validationService.validatePost(userId);

        return userProcessor.getOpenUserDTO(userName, profilePictureUrl, mediaPostsUrl, countFollowing, countFollowers, countPosts);
    }

    @Override
    public void updateUserProfile(Long userId, UserDTO userDTO) throws UsernameIsBusyException, UserNotFoundException, EmailAlreadyTakenException, EmailBusyException, NumberParseException {
        validationService.validatePicture(userDTO);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = %s not found".formatted(userId)));
        String phoneNum = validationService.validateUpdateUser(userDTO, user);

        validationService.validatePassword(userDTO.getPassword());
        user.setUpdatedAt(LocalDateTime.now());
        userProcessor.userCreateProcessor(userDTO, user, phoneNum);
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        UserEvent userEvent = userProcessor.getUserEvent(user);

        kafkaTemplate.send("user.updated", user.getUserId(), userEvent);
    }

    @Override
    public void registerNewUser(UserDTO userDTO) throws UsernameIsBusyException, EmailAlreadyTakenException, EmailBusyException, NumberParseException {
        validationService.validatePicture(userDTO);
        validationService.validateUsername(userDTO.getUsername());
        validationService.validateEmail(userDTO.getEmail());
        validationService.validatePassword(userDTO.getPassword());

        String phoneNumber = validationService.validatePhoneNumber(userDTO.getPhoneNumber(), userDTO.getRegion());

        User registerUser = new User();
        registerUser.setCreatedAt(LocalDateTime.now());
        userProcessor.userCreateProcessor(userDTO, registerUser, phoneNumber);
        userRepository.save(registerUser);

        UserEvent event = userProcessor.getUserEvent(registerUser);

        kafkaTemplate.send("user.created", registerUser.getUserId(), event);
    }
}
