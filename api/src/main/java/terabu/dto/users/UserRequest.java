package terabu.dto.users;

import lombok.Data;

@Data
public class UserRequest {
    private String login;
    private String email;
    private String password;
}
