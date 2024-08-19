package org.thewhitemage13.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.dto.GetUserDTO;
import org.thewhitemage13.dto.OpenUserDTO;
import org.thewhitemage13.dto.UserDTO;
import org.thewhitemage13.dto.UserSubscriptionDTO;
import org.thewhitemage13.exception.EmailAlreadyTakenException;
import org.thewhitemage13.exception.UserNotFoundException;
import org.thewhitemage13.exception.UsernameIsBusyException;
import org.thewhitemage13.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-user-by-id")
    public ResponseEntity<GetUserDTO> getUserById(@RequestParam("userId") Long userId) {
        try {
            return ResponseEntity.ok(userService.getUserById(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add-users-list")
    public ResponseEntity<String> addUsers(@RequestBody List<UserDTO> users) {
        try {
            userService.addUsers(users);
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get-users-by-Ids")
    public List<UserSubscriptionDTO> getUsersByIds(@RequestParam("ids") List<Long> ids) {
        return userService.getUsersByIds(ids);
    }

    @GetMapping("/open-user/{userId}")
    public ResponseEntity<OpenUserDTO> openUser(@PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(userService.openUser(userId));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-username-by-id/{userId}")
    public ResponseEntity<String> getUserNameById(@PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(userService.getUsernameById(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id = %s not found".formatted(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/user-verification")
    public ResponseEntity<Boolean> userVerification(@RequestParam("userId") Long userId) {
        try {
            boolean isCreated = userService.userVerification(userId);
            return ResponseEntity.ok(isCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> registerNewUser(@RequestBody UserDTO userDTO) {
        try {
            userService.registerNewUser(userDTO);
            return ResponseEntity.ok("User created successfully");
        } catch (UsernameIsBusyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username = %s is busy!".formatted(userDTO.getUsername()));
        } catch (EmailAlreadyTakenException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email = %s is already taken!".formatted(userDTO.getEmail()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) {
        try {
            userService.updateUserProfile(userId, userDTO);
            return ResponseEntity.ok("User updated");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with id = %s not found!".formatted(userId));
        } catch (UsernameIsBusyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username = %s is busy!".formatted(userDTO.getUsername()));
        } catch (EmailAlreadyTakenException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email = %s is already taken!".formatted(userDTO.getEmail()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/get-information/{username}")
    public Object findUser(@PathVariable("username") String username) {
        try {
            return userService.getInformationAboutUser(username);
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with id = %s not found!");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam("userId") Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted");
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with id = %s not found!");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }
}
