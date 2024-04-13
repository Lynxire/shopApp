package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;
import terabu.entity.status.OrderStatus;

@Data
@Entity
@Table(schema = "app", name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId")
    private User user;


}
