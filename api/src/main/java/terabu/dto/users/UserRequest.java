package terabu.dto.users;

import jakarta.validation.constraints.Min;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class UserRequest {
    @NotBlank(message = "пустое поле логина")
    @Length(min = 4, max = 50)
    private String login;
    @NotBlank(message = "пустое поле почты")
    private String email;
    @NotBlank(message = "пустое поле пароля")
    @Length(min = 10)
    private String password;
}
