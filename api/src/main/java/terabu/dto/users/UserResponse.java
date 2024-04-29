package terabu.dto.users;

import lombok.Data;



@Data
public class UserResponse {
    private Long id;
    private String login;
    private String email;
    private String password;

}
