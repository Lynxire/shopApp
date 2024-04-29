package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;
import terabu.entity.status.Role;

import java.time.LocalDate;

@Entity
@Data
@Table(schema = "app", name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String email;
    private String password;
    @Column(name = "data_registration")
    private LocalDate dateRegistration;
    @Enumerated(EnumType.STRING)
    private Role role;



}
