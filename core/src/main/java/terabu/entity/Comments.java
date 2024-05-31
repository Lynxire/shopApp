package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;
import terabu.dto.users.UserDTO;

@Data
@Entity
@Table(schema = "app", name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_id")
    @SequenceGenerator(name = "comments_id", sequenceName = "comments_seq", allocationSize = 1, schema = "app")
    private Long id;
    private String comments;
    @JoinColumn(name = "userId")
    private Long userId;

}
