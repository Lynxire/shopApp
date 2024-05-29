package terabu.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import terabu.dto.users.UserRequest;
import terabu.dto.users.UserResponse;


@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@Tag(name = "Контроллер пользователей")
public class UserController {
    private final RestTemplate restTemplate;

    @PostMapping("/registration")
    public ResponseEntity<UserResponse> registration(@Valid @RequestBody UserRequest userRequest) {
        return restTemplate.postForEntity("http://localhost:8081/user/registration", userRequest, UserResponse.class);
    }

    @PostMapping("/authorization")
    public ResponseEntity<UserResponse>  authorization(@Valid @RequestBody UserRequest userRequest) {
        return restTemplate.postForEntity("http://localhost:8081/user/authorization", userRequest, UserResponse.class);

    }


}
