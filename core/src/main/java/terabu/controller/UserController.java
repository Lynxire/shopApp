package terabu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import terabu.entity.User;
import terabu.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/registration")
    public void registration(@RequestBody User user) {
        userService.registerUser(user);
    }

}
