package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "app", name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long sum;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;
}
