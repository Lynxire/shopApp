package terabu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import terabu.dto.users.UserRequest;
import terabu.dto.users.UserResponse;
import terabu.entity.User;
import terabu.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;


    @PostMapping("/registration")
    public UserResponse registration(@RequestBody UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }

    @PostMapping("/login")
    public void authentication(@RequestBody User user) {
        if(user.getEmail() == null || user.getPassword() == null) {
              throw new RuntimeException("Email and password are required");
        }
        userService.authenticate(user.getEmail(), user.getPassword());
    }

}
