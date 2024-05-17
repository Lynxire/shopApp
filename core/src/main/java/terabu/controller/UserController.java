package terabu.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Контроллер пользователей")
public class UserController {
    private final UserService userService;


    @PostMapping("/registration")
    public UserResponse registration(@RequestBody UserRequest userRequest) {
        return userService.registerNewUserAccount(userRequest);
    }


}
