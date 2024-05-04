package terabu.dto.data;

import lombok.Data;

@Data
public class UserDataRequest {
    private String name;
    private String surname;
    private String password;
    private String email;
    private String login;
    private Long userId;
}
