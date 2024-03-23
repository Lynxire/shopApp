package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "app", name = "user")
public class User {
    @Id
    private Long id;

    private String login;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
