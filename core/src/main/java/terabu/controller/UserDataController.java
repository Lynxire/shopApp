package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import terabu.dto.data.UserDataRequest;
import terabu.dto.data.UserDataResponse;
import terabu.service.UserDataService;

@RestController
@RequiredArgsConstructor
@RequestMapping("data")
@Tag(name = "Контроллер для доп. информации пользователя")
public class UserDataController {
    private final UserDataService userDataService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/update")
    @Operation(summary = "Обновление личной информации пользователя", description = "Данный метод для обновления личной информации и данных для входа")
    public UserDataResponse updateData(@RequestBody @Valid UserDataRequest userDataRequest){
        return userDataService.update(userDataRequest);
    }
}
