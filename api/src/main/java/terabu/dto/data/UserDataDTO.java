package terabu.dto.data;

import lombok.Data;
import terabu.dto.users.UserDTO;
@Data
public class UserDataDTO {
    private Long id;
    private String name;
    private String surname;
    private Long orders;
    private Long userId;
}
