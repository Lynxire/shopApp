package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import terabu.dto.data.UserDataRequest;
import terabu.dto.data.UserDataResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("data")
@Tag(name = "Контроллер для доп. информации пользователя")
public class UserDataController {
    private final RestTemplate restTemplate;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/update")
    @Operation(summary = "Обновление личной информации пользователя", description = "Данный метод для обновления личной информации и данных для входа")
    public ResponseEntity<UserDataResponse> updateData(@RequestBody @Valid UserDataRequest userDataRequest){
        return restTemplate.postForEntity("http://localhost:8081/data/update", userDataRequest, UserDataResponse.class);
    }
}
