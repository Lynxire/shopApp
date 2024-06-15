package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;
import terabu.dto.users.UserDTO;

@Data
@Entity
@Table(schema = "app", name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long sum;

    @JoinColumn(name = "userId")
    private Long userId;
}
