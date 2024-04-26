package terabu.dto.users;

import lombok.Data;

import javax.management.relation.Role;
@Data
public class UserResponse {
    private Long id;
    private String login;
    private String email;
    private String password;

}
