package terabu.entity;

import jakarta.persistence.*;
import terabu.dto.users.UserDTO;

@lombok.Data
@Entity
@Table(schema = "app", name = "data")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Long orders;
    @JoinColumn(name = "userId")
    private Long userId;
}
