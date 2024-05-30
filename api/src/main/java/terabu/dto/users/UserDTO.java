package terabu.dto.users;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserDTO {
    private Long id;
    private String login;
    private String email;
    private String password;
    private LocalDate dateRegistration;
    private String role;
}
