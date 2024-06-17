package terabu.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import terabu.dto.users.UserRequest;
import terabu.dto.users.UserToken;


@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@Tag(name = "Контроллер пользователей")
public class UserController {
    private final RestTemplate restTemplate;

    @Value("${user.url}")
    private String url;
    private String urlReg;
    private String urlLogin;

    @PostConstruct
    public void init() {
        urlReg = url + "/registration";
        urlLogin = url + "/authorization";
    }


    @PostMapping("/registration")
    public ResponseEntity<UserToken> registration(@Valid @RequestBody UserRequest userRequest) {
        return restTemplate.postForEntity(urlReg, userRequest, UserToken.class);
    }

    @PostMapping("/authorization")
    public ResponseEntity<UserToken>  authorization(@Valid @RequestBody UserRequest userRequest) {
        return restTemplate.postForEntity(urlLogin, userRequest, UserToken.class);

    }


}
