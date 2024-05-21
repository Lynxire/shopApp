package terabu.dto.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDataRequest {
    private String name;
    private String surname;
    @Length(min = 10)
    private String password;
    private String email;
    @Length(min = 4, max = 50)
    private String login;
    @NotNull
    private Long userId;
}
