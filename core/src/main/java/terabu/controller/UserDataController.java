package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @PostMapping("/update")
    @Operation(summary = "Обновление личной информации пользователя")
    public UserDataResponse updateData(@RequestBody UserDataRequest userDataRequest){
        return userDataService.update(userDataRequest);
    }
}
