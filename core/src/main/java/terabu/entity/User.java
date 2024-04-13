package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;
import terabu.entity.status.Role;

@Entity
@Data
@Table(schema = "app", name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
