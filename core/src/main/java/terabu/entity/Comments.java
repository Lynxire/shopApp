package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "app", name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comments;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId")
    private User user;
}
