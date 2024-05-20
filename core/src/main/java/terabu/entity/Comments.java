package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "app", name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Sequence
    private Long id;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
